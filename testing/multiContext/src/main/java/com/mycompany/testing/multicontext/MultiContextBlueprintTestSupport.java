package com.mycompany.testing.multicontext;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.jar.Manifest;

import org.apache.camel.CamelContext;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.camel.util.ObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiContextBlueprintTestSupport extends CamelBlueprintTestSupport {
	Logger log = LoggerFactory.getLogger(MultiContextBlueprintTestSupport.class);

	/**
	 * If more than one Camel contexts are available, set the name of the
	 * primary one here. This will be accessible through "context" member in the
	 * test class
	 */
	protected String getCamelContextToTest() {
		return null;
	}

	/**
	 * Give the id(s) of additional Camel context that should be started during
	 * the test. Comma separated list is accepted.
	 */
	protected String getCamelContextToStart() {
		return null;
	}

	/**
	 * Set a bundle filter to avoid scanning of /target/classes for blueprint
	 * xmls. So that only the xmls listed in getBlueprintDescriptor() are used
	 * and avoid creating the blueprint context twice.
	 * 
	 * Filter expression built:
	 * "(!(Bundle-SymbolicName=[symblic name in MANIFEST.MF])"
	 */
	@Override
	protected String getBundleFilter() {
		String answer = null;

		URI manifestPath = Paths.get(System.getProperty("basedir"), "target", "classes", "META-INF", "MANIFEST.MF")
				.toUri();

		try {
			// Bundle-SymbolicName from MANIFEST.MF
			Manifest manifest = new Manifest(new FileInputStream(new File(manifestPath)));
			String symblicName = manifest.getMainAttributes().getValue("Bundle-SymbolicName");
			if (symblicName != null) {
				answer = String.format("(!(Bundle-SymbolicName=%s))", symblicName);
			}
		} catch (Exception e) {
			log.warn("Bundle-SymbolicName was not found in: " + manifestPath, e);
		}

		if (answer == null) {
			log.warn("No Bundle-SymbolicName was found in {}.", manifestPath);
		} else {
			log.debug("Bundle scan filter: {}", answer);
		}

		return answer;
	};

	/**
	 * Give back the Camel context with the name set in getCamelContextToTest();
	 * If no name was set, will give back one Camel context (like
	 * CamelBlueprintTestSupport does)
	 */
	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext answer = null;
		if (getCamelContextToTest() != null) {
			log.debug("Getting Camel context to test: {}", getCamelContextToTest());
			answer = this.getOsgiService(CamelContext.class, "(camel.context.name=" + getCamelContextToTest() + ")");
			log.info("Camel context to test: {}", answer.toString());
		} else {
			// as in CamelBlueprintTestSupport
			answer = super.createCamelContext();
		}
		return answer;
	}

	/**
	 * Start the Camel contexts set in getCamelContextToStart(),
	 */
	@Override
	protected void doPostSetup() throws Exception {
		if (getCamelContextToStart() != null) {
			Iterator<Object> it = ObjectHelper.createIterator(getCamelContextToStart());
			while (it.hasNext()) {
				String contextName = ((String) it.next()).trim();
				log.debug("Getting Camel context to start: {}", contextName);
				CamelContext context = this.getOsgiService(CamelContext.class,
						"(camel.context.name=" + contextName + ")");
				log.info("Starting Camel context: {}", context.toString());
				context.start();
			}
		}
	}
}

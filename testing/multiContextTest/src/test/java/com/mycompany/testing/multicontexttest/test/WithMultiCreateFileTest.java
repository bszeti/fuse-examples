package com.mycompany.testing.multicontexttest.test;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;
import com.mycompany.testing.multicontext.MultiContextBlueprintTestSupport;

/**
 * The test verifies that only one blueprint context is created from the
 * project's xmls listed in getBlueprintDescriptor (plus the ones in dependency
 * jars). Checks that xmls under main/resources was ignored and main.testOut was
 * not created.
 */
public class WithMultiCreateFileTest extends MultiContextBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/multiContextTest-camel.xml,/OSGI-INF/blueprint/multiContextTest-testbeans.xml";

	};

	@Test
	public void NoMainFileIsCreated() throws Exception {
		Thread.sleep(2000);

		File fileMain = new File(Paths.get(System.getProperty("basedir"), "target", "main.testOut").toUri());
		File fileTest = new File(Paths.get(System.getProperty("basedir"), "target", "test.testOut").toUri());
		assertFalse(fileMain.exists());
		assertTrue(fileTest.exists());

		fileTest.delete();
	}

}

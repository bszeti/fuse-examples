package com.mycompany.testing.multicontexttest.test;

import java.io.File;
import java.nio.file.Paths;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import com.mycompany.testing.multicontext.MultiContextBlueprintTestSupport;

/**
 * This test verifies the behavior of CamelBlueprintTestSupport that it creates
 * two blueprint contexts: one with the xmls at main/resources and also another
 * one from the xmls listed in getBlueprintDescriptor(). The test check that
 * both files created by MyBean.init exists. With
 * MultiContextBlueprintTestSupport only one file is created as main/resources
 * in ignored. See WithMultiCreateFileTest.java.
 * 
 * Also note in the test logs that "context-directCommon" was started instead of
 * "context-multiContextTest" (that you would normally expect) simply because
 * that context was created first (see the enforced delay in MyBean.init).
 * CamelBlueprintTestSupport supports only one Camel context.
 * MultiContextBlueprintTestSupport picks the Camel context by id which solves
 * this problem.
 *
 */
public class CreateFileTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/multiContextTest-camel.xml,/OSGI-INF/blueprint/multiContextTest-testbeans.xml";

	};

	@Test
	public void BothFilesAreCreated() throws Exception {
		Thread.sleep(2000);

		File fileMain = new File(Paths.get(System.getProperty("basedir"), "target", "main.testOut").toUri());
		File fileTest = new File(Paths.get(System.getProperty("basedir"), "target", "test.testOut").toUri());
		assertTrue(fileMain.exists());
		assertTrue(fileTest.exists());

		fileMain.delete();
		fileTest.delete();
	}

}

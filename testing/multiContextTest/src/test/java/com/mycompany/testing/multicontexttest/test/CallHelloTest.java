package com.mycompany.testing.multicontexttest.test;

import org.apache.camel.ExchangePattern;
import org.junit.Test;

import com.mycompany.testing.multicontext.MultiContextBlueprintTestSupport;

/**
 * This test verifies that our route can call a direct-vm endpoint created by
 * another Camel context, so that both context are started.
 *
 */
public class CallHelloTest extends MultiContextBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/multiContextTest-camel.xml";

	};

	@Override
	protected String getCamelContextToTest() {
		return "context-multiContextTest";
	}

	@Override
	protected String getCamelContextToStart() {
		return "context-directCommon";
	}

	@Test
	public void CallHello() {
		String response = template.sendBody("direct:call-hello", ExchangePattern.InOut, "World!").toString();
		assertEquals("Hello World!", response);
	}

}

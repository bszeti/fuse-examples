package com.mycompany.cache.cacheComponent;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;
import org.junit.Test;

public class CacheTest extends CamelBlueprintTestSupport {
	private ExchangeBuilder builder;

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/cacheComponent.xml";
	}

	@Override
	protected String[] loadConfigAdminConfigurationFile() {
		//Overwrite properties for the test (expireTime)
		return new String[] { "src/test/resources/configuration/com.mycompany.cache.cacheComponent.cfg",
				"com.mycompany.cache.cacheComponent" };

	};

	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@Before
	public void prepare() throws Exception {
		builder = new ExchangeBuilder(context);

		// Insert a mock endpoint into the route
		context.getRouteDefinition("doTheQuery").adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				weaveAddFirst().to("mock:doTheQuery");

			}
		});
	}

	@Test
	public void testCacheIsUsed() {
		Exchange exchange = builder.withBody("AA").build();
		MockEndpoint mockDoTheQuery = getMockEndpoint("mock:doTheQuery");

		// Actually response is the same Exchange object as request
		Exchange response = template.send("direct:incomingRequest", exchange);
		assertEquals("QueryResponse-AA", response.getIn().getBody(String.class));
		assertListSize(mockDoTheQuery.getExchanges(), 1);

		exchange = builder.withBody("BB").build();
		response = template.send("direct:incomingRequest", exchange);
		assertEquals("QueryResponse-BB", response.getIn().getBody(String.class));
		assertListSize(mockDoTheQuery.getExchanges(), 2);

		// Sending earlier request again, so cache is used
		exchange = builder.withBody("AA").build();
		response = template.send("direct:incomingRequest", exchange);
		assertEquals("QueryResponse-AA", response.getIn().getBody(String.class));
		assertListSize(mockDoTheQuery.getExchanges(), 2);
	}
	
	@Test
	public void testCacheExpires() throws InterruptedException {
		Exchange exchange = builder.withBody("AA").build();
		MockEndpoint mockDoTheQuery = getMockEndpoint("mock:doTheQuery");

		// Actually response is the same Exchange object as request
		Exchange response = template.send("direct:incomingRequest", exchange);
		assertEquals("QueryResponse-AA", response.getIn().getBody(String.class));
		assertListSize(mockDoTheQuery.getExchanges(), 1);
		
		//Wait for the cache to expire
		Thread.sleep(2500L);

		// Sending earlier request again, so cache is used
		exchange = builder.withBody("AA").build();
		response = template.send("direct:incomingRequest", exchange);
		assertEquals("QueryResponse-AA", response.getIn().getBody(String.class));
		assertListSize(mockDoTheQuery.getExchanges(), 2);
	}

}

package ${package};

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteTest extends CamelBlueprintTestSupport {

    Logger log = LoggerFactory.getLogger(RouteTest.class);

    public RouteTest() {
      super();
      System.setProperty("java.protocol.handler.pkgs", "org.ops4j.pax.url");
    }

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/${artifactId}.xml,/OSGI-INF/blueprint/test-property-placeholder.xml";
    }

    @Test
    public void testRoute() throws Exception {
        //Implement your test here. An example:
        MockEndpoint mock = this.getMockEndpoint("mock:end");
        mock.expectedMessageCount(2);
        this.assertMockEndpointsSatisfied();
    }

}

package examples.wmq.wmqcomponent;

import javax.jms.ConnectionFactory;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.spi.ComponentResolver;
import org.springframework.jms.support.destination.DestinationResolver;

public class WmqComponentResolver implements ComponentResolver {
	
	private ConnectionFactory connectionFactory;

	public Component resolveComponent(String name, CamelContext context) throws Exception {
		
		JmsConfiguration jmsConfiguration = new JmsConfiguration(connectionFactory);
		//optionally can set other setting on jmsConfiguration...
		
		JmsComponent jmsComponent = new JmsComponent(jmsConfiguration);
		
		return jmsComponent;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}


}

WebSphere MQ example
=====================

This project shows an example how WebSphere MQ can be used with Red Hat Jboss Fuse.

If multiple bundles have routes using JMS connections for a WMQ broker, it's easier to define the JMS connection factory in one bundle and publish it as an OSGI service instead of defining a connection factory in each bundles. The bundles containing the route definitions can refer this shared connection factory and use it in their JmsComponent.

If the required JmsComponents are similar, a custom "wmq" component provider can be created (like the built in "amq" component provider). In this case the bundles with routes don't have to contain a definition for a JMS connection factory or JmsComponent, but simply can use "wmq:QUEUE_NAME" endpoint URIs.

In some cases (for example using topics with durable subscribers) the shared connection factory is not convenient and a separate pool is required per component.

Bundles
-------
* wmqComponent: WebSphere MQ connection factory and "wmq" component provider. Publishes OSGI services used by the example routes.
* wmqConnFactoryTest: Route handling HTTP requests (http://localhost:9090/wmqConnFactoryTest), using the published JMS connection factory to connect to broker.
* wmqTest: Route handling HTTP requests (http://localhost:9090/wmqTest), using only "wmq:QUEUE_NAME" URIs.

The MQ broker connection parameters can be set in config "examples.wmq.wmqcomponent", see my defaults in wmqComponent.xml. The routes uses queue "QUEUE1", so it should be defined in the queue manager. (How to set up the queue manager properly is not covered here...)

WMQ libs
--------
WebSphere MQ OSGI client libraries are required to connect to an MQ broker, which can be found as part of WebSphere MQ clients.

The easiest is to first install the libs into the local Maven respository. See example script "installWmqLibsToMaven.sh". I used custom maven coordinates for the artifacts, as I couldn't find the offical IBM naming.

Build
-------
All the example bundles can be built by running  "mvn clean install" in the high level "wmq" parent directory.  
The projects are dependent on jboss.fuse.bom.version 6.2.0.redhat-133, so please first add the public Fuse maven repo to your maven settings.xml: http://repo.fusesource.com/nexus/content/groups/public/

After a successful build that installs the generated bundles into the local maven repo, the example can be deployed in a local Fuse container.

Install
-------
This example was tested on Red Hat JBoss Fuse v6.2 (jboss-fuse-6.2.0.redhat-133).

Add the generated feature repository in Fuse:  
 `features:addurl mvn:examples.wmq/features/1.0.0-SNAPSHOT/xml/features`  
 Install feature 'wmq-routes' that also installs the required WMQ client libraries.   
 `features:install wmq-routes`

Test
----
Send an HTTP request to http://localhost:9090/wmqTest or http://localhost:9090/wmqConnFactoryTest. The response should contain the request URL, body and a "Response from Camel" line at the end. For example sending a simple GET from a browser shows:
<pre>
/wmqConnFactoryTest

Response from Camel
</pre>

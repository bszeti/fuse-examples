JDBC datasources
================

Examples how to define JDBC datasources and share them as OSGI services. Bundles requiring connections to the databases can use these shared objects in their Camel components. This way all the required configuration (host, port, credentails) and the connection pooling is managed in one bundle and the "business bundles" don't need to know about the details of the datasource.

## Shared datasource
A shared datasource can be created and shared by a bundle (see ./datasource/) or by using Karaf jdbc commands (https://karaf.apache.org/manual/latest/users-guide/jdbc.html).

See modules under /datasource:
- myderby: creates a shared embedded Apache Derby DB datasource
- myoracle: a shared Oracle datasource example
- dbpool: an example how to use OPS4J Pax JDBC to maintain a connection pool in OSGi environment. Using Apache Commons DBCP causes classloader issues in some cases, which works fine using the pax-jdbc features. See features file for required features and dependencies.

## Use a datasource
The datasource can be used directly in blueprint and in Camel components. See examples in "jdbcTest":
- myDataSource: a datasource defined in the bundle using property-placeholder values optionally used by other bundles. This is just an example how to use multiple property-placeholders in one blueprint file.
- sharedDataSource: shared simple datasource
- pooledDataSource: shared datasource using pax-jdbc pooling

A shared datasource can also be used in MyBatis as a "jndi" resource (required "jndi" feature installed). See "mybatisJndi" and it's "SqlMapConfig.xml".

## Build and install
The modules under "jdbc" can be installed simply by calling "mvn clean install". They require the parent pom one level above.

Deployment to Fuse 6.2.1 can be done via the feature file:
- JBossFuse:karaf@root> features:addurl mvn:com.mycompany.jdbc/features/1.0.0-SNAPSHOT/xml/features
- JBossFuse:karaf@root> features:install com.mycompany.jdbc

Expected lines in log:
- Mybatis query result: {1=1}
- Shared ds: [{1=1}]
- Bundle's ds: [{1=1}]
- Pooled ds: [{1=1}]

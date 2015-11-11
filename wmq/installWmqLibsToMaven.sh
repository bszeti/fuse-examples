#!/bin/sh

mvn install:install-file -Dfile=com.ibm.mq.osgi.directip_7.5.0.2.jar -DgroupId=com.ibm.mq.osgi -DartifactId=directip -Dversion=7.5.0.2 -Dpackaging=jar 
mvn install:install-file -Dfile=com.ibm.mq.osgi.java_7.5.0.2.jar -DgroupId=com.ibm.mq.osgi -DartifactId=java -Dversion=7.5.0.2 -Dpackaging=jar 
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.commonservices.j2se_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=commonservices.j2se -Dversion=7.5.0.2 -Dpackaging=jar 
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.jms_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=jms -Dversion=7.5.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.jms.prereq_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=jms.prereq -Dversion=7.5.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.nls_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=nls -Dversion=7.5.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.wmq_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=wmq -Dversion=7.5.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.wmq.nls_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=wmq.nls -Dversion=7.5.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.msg.client.osgi.wmq.prereq_7.5.0.2.jar -DgroupId=com.ibm.msg.client.osgi -DartifactId=wmq.prereq -Dversion=7.5.0.2 -Dpackaging=jar

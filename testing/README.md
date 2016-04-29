Enhance CamelBlueprintTestSupport
=================================
It's great to have CamelBlueprintTestSupport for simple unit testing, but it has its limitations.

This project contains a MultiContextBlueprintTestSupport which eliminates two problems with CamelBlueprintTestSupport.

## Multiple Camel contexts
CamelBlueprintTestSupport supports only one Camel context. It picks the "first" available and starts that for the unit test. This can cause problems if we have blueprint xmls in dependency jars:
- Based on racing conditions a Camel context from one of the dependencies can be started instead of our context defined in the project
- We can't start more than one Camel context for the test

MultiContextBlueprintTestSupport has some added methods to handle this problems:
 - getCamelContextToTest(): set the id for the primary Camel context set as "context" variable in the test
 - getCamelContextToStart(): set the id of additional Camel contexts to start during the test (these must be in dependecy jars). Comma separated list is supported.

See multiContextTest project's 'CallHelloTest' unit test for an example how to use.

## Blueprint context is created twice
During startup CamelBlueprintTestSupport scans the bundles for "OSGI-INF/blueprint/*.xml" files and creates blueprint contexts accordingly:
- uses target/classes as a bundle
- checks dependency jar bundles
- adds a bundle with the xmls listed in getBlueprintDescriptor()

The 'target/classes' folder contains the blueprint xmls from main/resources, so if they are also listed in getBlueprintDescriptor() they will be used in two created bundles. It's not confirmed if this behavior is a "bug or a feature", but most of the CamelBlueprintTestSupport tutorials suggest to list the blueprint xmls you want to use for your test blueprint context. This causes two problems:
- If the beans uses unique resources (e.g. ports), resource allocation exceptions will arrise.
- If we have a Camel context in blueprint, it's created twice and a random one is started (as now we have multiple Camel contexts available - see above).

MultiContextBlueprintTestSupport solves this problem by ignoring target/classes folder for blueprint xmls.

See multiContextTest's project 'CreateFileTest' that uses CamelBlueprintTestSupport and creates two files during initiliazion, while we expected only one. WithMultiCreateFileTest is the same test extending MultiContextBlueprintTestSupport that resolves the problem: only one file is created.

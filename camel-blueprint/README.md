Fuse projects archetype
=======================

Maven project archetype for Fuse Camel projects.
 - Uses parent pom
 - Has a simple blueprint XML route and test

Generate project:
- mvn archetype:generate -DarchetypeGroupId=com.mycompany.archetypes -DarchetypeArtifactId=camel-blueprint -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.mycompany -DartifactId=MyArtifact -Dversion=1.0.0-SNAPSHOT -Dpackage=com.mycompany.myartifact

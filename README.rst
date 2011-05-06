========================
Cloudera Maven Archetype
========================

This project is a collection of Maven archetype templates. That is, the projects are intended to be read by the
maven-archetype-plugin to produce an archetype.

Archetypes are published to Cloudera's Maven repository at

 https://repository.cloudera.com/content/repositories/releases/

for releases, and 

 https://repository.cloudera.com/content/repositories/snapshots/

for SNAPSHOTs.

MapReduce in < 100 Keystrokes
=============================

You can create a template MapReduce executable jar using the ``mapred-driver-archetype``.

Here's how you do it (you'll need a recent version of Maven installed)::

 $ mvn archetype:generate -DarchetypeCatalog=http://repository.cloudera.com/archetype-catalog.xml
 [INFO] Scanning for projects...
 [INFO]                                                                         
 [INFO] ------------------------------------------------------------------------
 [INFO] Building Maven Stub Project (No POM) 1
 [INFO] ------------------------------------------------------------------------
 [INFO] 
 [INFO] >>> maven-archetype-plugin:2.0:generate (default-cli) @ standalone-pom >>>
 [INFO] 
 [INFO] <<< maven-archetype-plugin:2.0:generate (default-cli) @ standalone-pom <<<
 [INFO] 
 [INFO] --- maven-archetype-plugin:2.0:generate (default-cli) @ standalone-pom ---
 [INFO] Generating project in Interactive mode
 [INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
 Choose archetype:
 1: http://repository.cloudera.com/archetype-catalog.xml -> mapred-driver-archetype (mapred-driver-archetype)
 Choose a number: : 1
 Define value for property 'groupId': : foo
 Define value for property 'artifactId': : foo
 Define value for property 'version':  1.0-SNAPSHOT: : <enter>
 Define value for property 'package':  foo: : <enter>
 Confirm properties configuration:
 groupId: foo
 artifactId: foo
 version: 1.0-SNAPSHOT
 package: foo
  Y: : <enter>
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD SUCCESS
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time: 10.501s
  [INFO] Finished at: Wed Apr 27 22:20:16 EDT 2011
  [INFO] Final Memory: 7M/81M
  [INFO] ------------------------------------------------------------------------
 

Now you've got a Maven-based Hadoop MapReduce project that links to CDH3 artifacts.

Building and running
--------------------

 $ cd foo
 $ mvn package
 $ hadoop jar target/foo-1.0-SNAPSHOT-driver.jar

Running unit tests
------------------

 $ mvn test

Importing your project in Eclipse
---------------------------------

 $ mvn eclipse:eclipse -DdownloadSources

Then, in Eclipse, go: Import... > Existing Projects... > select the 'foo' directory (or parent thereof) > done!
The nice thing about using Maven here is that it links the sources for you.


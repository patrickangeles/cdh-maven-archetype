-- TODO: Edit this README --


Did some twiddling around with Maven archetypes and catalogs. You can now create a template hadoop executable jar that has a Driver with two MapReduce jobs: wordcount based on the old *.mapred.* API, and the same wordcount based on the new *.mapreduce.* API.

Here's how you do it (you'll need a recent if not latest version of Maven installed):

$ mvn archetype:generate -DarchetypeCatalog=http://repository.cloudera.com
[INFO] Scanning for projects...
[INFO] Searching repository for plugin with prefix: 'archetype'.
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Default Project
[INFO]    task-segment: [archetype:generate] (aggregator-style)
[INFO] ------------------------------------------------------------------------
[INFO] Preparing archetype:generate
[INFO] No goals needed for project - skipping
[INFO] Setting property: classpath.resource.loader.class => 'org.codehaus.plexus.velocity.ContextClassLoaderResourceLoader'.
[INFO] Setting property: velocimacro.messages.on => 'false'.
[INFO] Setting property: resource.loader => 'classpath'.
[INFO] Setting property: resource.manager.logwhenfound => 'false'.
[INFO] [archetype:generate {execution: default-cli}]
[INFO] Generating project in Interactive mode
[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
Choose archetype:
1: http://repository.cloudera.com -> mapred-driver-archetype (mapred-driver-archetype)
Choose a number:  (1): 1
Define value for groupId: : com.adfasdf
Define value for artifactId: : foobar
Define value for version:  1.0-SNAPSHOT: : <enter>
Define value for package:  com.adfasdf: : <enter>
Confirm properties configuration:
groupId: com.adfasdf
artifactId: foobar
version: 1.0-SNAPSHOT
package: com.adfasdf
 Y: : <enter>
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 28,column 12] : ${hadoop.releases.repo} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 38,column 12] : ${hadoop.snapshots.repo} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 51,column 16] : ${hadoop.version} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 57,column 16] : ${hadoop.version} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 63,column 16] : ${hadoop.version} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 69,column 16] : ${junit.version} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 90,column 18] : ${maven-assembly-plugin.version} is not a valid reference.
 [WARNING] org.apache.velocity.runtime.exception.ReferenceException: reference : template = archetype-resources/pom.xml [line 100,column 29] : ${basedir} is not a valid reference.
 [INFO] ------------------------------------------------------------------------
 [INFO] BUILD SUCCESSFUL
 [INFO] ------------------------------------------------------------------------

 Now you've got a maven-based Hadoop project! (Ignore the velocity ReferenceExceptions... those are normal.)

 To build and run in Hadoop:

 $ cd foobar
 $ mvn package
 $ hadoop jar target/foobar-1.0-SNAPSHOT-driver.jar

 To run the unit tests:

 $ mvn test

 To import the project in Eclipse:

 $ mvn eclipse:eclipse -DdownloadSources

 Then, in Eclipse, go: Import... > Existing Projects... > select the 'foobar' directory (or parent thereof) > done! The nice thing about using Maven here is that it links the sources for you.



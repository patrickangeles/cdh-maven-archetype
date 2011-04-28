-- TODO: Edit this README --


Did some twiddling around with Maven archetypes and catalogs. You can now create a template hadoop executable jar that has a Driver with two MapReduce jobs: wordcount based on the old *.mapred.* API, and the same wordcount based on the new *.mapreduce.* API.

Here's how you do it (you'll need a recent if not latest version of Maven installed):

$ mvn archetype:generate -DarchetypeCatalog=http://cdh-maven-repo.googlecode.com/svn/trunk/archetype-catalog.xml
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
1: http://cdh-maven-repo.googlecode.com/svn/trunk/archetype-catalog.xml -> mapred-driver-archetype (mapred-driver-archetype)
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
$ 



 Now you've got a maven-based Hadoop project! (Ignore the velocity ReferenceExceptions... those are normal.)

 To build and run in Hadoop:

 $ cd foobar
 $ mvn package
 $ hadoop jar target/foobar-1.0-SNAPSHOT-driver.jar

 To run the unit tests:

 $ mvn test

 To import the project in Eclipse:

 $ mvn eclipse:eclipse -DdownloadSources

 Then, in Eclipse, go: Import... > Existing Projects... > select the 'foo' directory (or parent thereof) > done! The nice thing about using Maven here is that it links the sources for you.



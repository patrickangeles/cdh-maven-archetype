This ant project allows you to create/install/deploy Maven artifacts
from an expanded CDH tarball. It's been tested to work for CDH2
tarballs.

The build.xml makes some assumptions on the directory locations and
file naming conventions in CDH and tries to make a best guess to
derive the appropriate Maven-friendly artifact name and version.

The default target is 'install' to your local Maven repo cache.
You can also 'deploy' to the Cloudera repos.

To install to your local .m2 repo:

  ant -Dcdh.dir=/path/to/hadoop-0.20.1+169.68

Note that you need to specify cdh.dir either in the command line or in
build.properties. This is the location of the CDH version of Hadoop.
(The expanded tarball.)

The script will determine the version/build number (everything after 
the '+') and rename them to the following Maven-friendly convention:

  hadoop-core-0.20.1-CDH.169.68.jar

It will also package source jars as:

  hadoop-core-0.20.1-CDH.169.68-sources.jar

Finally, it will 'install' or 'deploy'  the artifacts that have a
corresponding pom-template.xml file and publish those to the
repository. For each artifact, the source jar will be attached
if one is found.



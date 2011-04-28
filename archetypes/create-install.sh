#!/bin/bash
#
# Script to create and install (in local repo) a mvn archetype from an existing project.
#

ARCHETYPE=$1

if [ "$ARCHETYPE" == "" ] ; then
  echo "Usage: create-install.sh <path-to-archetype-project>"
  exit 1
fi

cd $ARCHETYPE
mvn clean
mvn eclipse:clean
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn install

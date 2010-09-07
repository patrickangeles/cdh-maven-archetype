#!/bin/bash
#
# Script to create and install (in local repo) a mvn archetype from an existing project.
#

ARCHETYPE=$1

cd $ARCHETYPE
mvn clean
mvn eclipse:clean
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn install

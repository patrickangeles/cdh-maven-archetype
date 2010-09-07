#!/bin/bash
#
# Script to create and deploy an archetype from an existing mvn project.
#

ARCHETYPE=$1
LOCAL_PATH=$2

cd $ARCHETYPE
mvn clean
mvn eclipse:clean
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn deploy -DaltDeploymentRepository=cdh-maven::default::file:$LOCAL_PATH

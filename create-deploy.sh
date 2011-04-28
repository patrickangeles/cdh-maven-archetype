#!/bin/bash
#
# Script to create and deploy an archetype from an existing mvn project.
#
ARCHETYPE=$1

if [ "$ARCHETYPE" == "" ] ; then
  echo "Usage: create-deploy.sh <path-to-archetype-project>"
  exit 1
fi

cd $ARCHETYPE
mvn clean
mvn eclipse:clean
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn deploy

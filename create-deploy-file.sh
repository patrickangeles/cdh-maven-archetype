#!/bin/bash
#
# Script to create and deploy an archetype from an existing mvn project to a local directory.
# Full (not relative) paths should be used for the LOCAL_PATH argument.
#
#
ARCHETYPE=$1
LOCAL_PATH=$2

if [ "${ARCHETYPE}" == "" ] || [ "${LOCAL_PATH}" == "" ] ; then
  echo "Usage: create-deploy-file.sh <path-to-archetype-project> <deploy-dir>"
  exit 1
fi

if [ ! -e ${ARCHETYPE}/pom.xml ] ; then
  echo "${ARCHETYPE} is not a maven project directory"
  exit 1
fi

if [ ! -d ${LOCAL_PATH} ] ; then
  echo "${LOCAL_PATH} directory does not exist"
  exit 1
fi

cd $ARCHETYPE
mvn clean
mvn eclipse:clean
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn deploy -DaltDeploymentRepository=cdh-maven::default::file:$LOCAL_PATH

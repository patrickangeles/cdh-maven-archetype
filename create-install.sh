#!/bin/bash
#
# Copyright (c) 2011, Cloudera, Inc. All Rights Reserved.
#
# Cloudera, Inc. licenses this file to you under the Apache License,
# Version 2.0 (the "License"). You may not use this file except in
# compliance with the License. You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
# CONDITIONS OF ANY KIND, either express or implied. See the License for
# the specific language governing permissions and limitations under the
# License.
#

#
# Script to create and install (in the local .m2 repo) a mvn archetype from an existing project.
#

ARCHETYPE=$1

if [ "$ARCHETYPE" == "" ] ; then
  echo "Usage: create-install.sh <path-to-archetype-project>"
  exit 1
fi

if [ ! -e ${ARCHETYPE}/pom.xml ] ; then
  echo "${ARCHETYPE} is not a maven project directory"
  exit 1
fi

cd $ARCHETYPE
mvn clean
mvn eclipse:clean
mvn archetype:create-from-project
cd target/generated-sources/archetype
mvn install

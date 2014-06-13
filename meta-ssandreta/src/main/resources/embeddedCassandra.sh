#!/bin/bash

# Stratio Meta
#
# Copyright (c) 2014, Stratio, All rights reserved.
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 3.0 of the License, or (at your option) any later version.
#
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public
#  License along with this library.
#
# Stratio Meta CCM Test
# 
# PRE: Git, pyYaml module for python, Ant
pgrep -f casselfie
if [ $? -eq 0 ]; then
CURRENT_DIR=$(pwd)
MAVEN_HOME=mvn help:evaluate -Dexpression=settings.localRepository | grep -v '[INFO]'
COMPLETE_DIR="$MAVEN_HOME/casselfie/stratio-casselfie.jar"
DIR_NAME="`dirname $COMPLETE_DIR`"
cd $DIR_NAME
java -cp com.stratio.casselfie.Main.java stratio-casselfie.jar &
cd ${CURRENT_DIR}
fi

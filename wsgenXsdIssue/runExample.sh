#!/bin/bash
set -e

export JAVA_HOME=~/programs/java/jdk-11.0.16.1
export PATH=$JAVA_HOME/bin:$PATH
CLASSPATH="classes:lib/*"
JAVA_OPTS=""

rm -rf wsGenGenerated
rm -rf wsImportGenerated
rm -rf schemaGenerated
rm -rf classes
mkdir -p wsGenGenerated
mkdir -p wsImportGenerated
mkdir -p schemaGenerated
mkdir -p classes

find ./src -name "*.java" > sources.txt
javac -d classes -cp $CLASSPATH @sources.txt

java $JAVA_OPTS -cp $CLASSPATH com.sun.tools.jxc.SchemaGenerator -d schemaGenerated ./src/test/xml/*

java $JAVA_OPTS -cp $CLASSPATH com.sun.tools.ws.WsGen test.ProviderMaintenanceServiceStronglyTypedEjb -wsdl -r wsGenGenerated -s wsGenGenerated -d wsGenGenerated 

java $JAVA_OPTS -cp $CLASSPATH com.sun.tools.ws.WsImport -d wsImportGenerated -keep wsGenGenerated/ProviderMaintenanceServiceStronglyTyped.wsdl

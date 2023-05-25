#!/bin/bash
set -e

export JAVA_HOME=~/programs/java/jdk-11.0.16.1
export PATH=$JAVA_HOME/bin:$PATH
CLASSPATH="classes:lib/*"
#JAVA_OPTS="$DEBUG"

function compile {
    find ./src -name "*.java" > sources.txt
    rm -rf classes
    mkdir -p classes
    javac -d classes -cp $CLASSPATH @sources.txt
}

function schemaGenerated {
    rm -rf schemaGenerated
    mkdir -p schemaGenerated
    java $JAVA_OPTS -cp $CLASSPATH com.sun.tools.jxc.SchemaGenerator -d schemaGenerated ./src/test/xml/*
}

function wsGenGenerated {
    rm -rf wsGenGenerated
    mkdir -p wsGenGenerated
    java $JAVA_OPTS -cp $CLASSPATH com.sun.tools.ws.WsGen test.ProviderMaintenanceServiceStronglyTypedEjb -wsdl -r wsGenGenerated -s wsGenGenerated -d wsGenGenerated 
}

function wsImportGenerated {
    rm -rf wsImportGenerated
    mkdir -p wsImportGenerated
    java $JAVA_OPTS -cp $CLASSPATH com.sun.tools.ws.WsImport -d wsImportGenerated -keep wsGenGenerated/ProviderMaintenanceServiceStronglyTyped.wsdl
}

compile
schemaGenerated
wsGenGenerated
wsImportGenerated



#!/bin/bash
set -e

mvn clean install

# Modify next 2 variables according with your paths
ORACLE_HOME="/home/jbescos/workspace/weblogic/depot/src151100_build/Oracle_Home"
JAVA_HOME=~/programs/java/jdk-17.0.5

PATH=$JAVA_HOME/bin:$PATH
JAVA_OPTS=""
CLASSPATH="$ORACLE_HOME/wlserver/server/lib/weblogic.jar:$ORACLE_HOME/oracle_common/modules/*:$ORACLE_HOME/wlserver/modules/clients/*"
COMPILED_DIR="target/classes"
WSDLGEN_DIR="target/wsdl"
SERVICESGEN_DIR="target/services"

mkdir -p $WSDLGEN_DIR
mkdir -p $SERVICESGEN_DIR

#java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR test.Main
java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR com.sun.tools.ws.WsGen com.oracle.medrec.facade.broker.JaxWsPatientFacadeBroker -wsdl -r $WSDLGEN_DIR -s $WSDLGEN_DIR -d $WSDLGEN_DIR -inlineSchemas
java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR com.sun.tools.ws.WsGen com.oracle.medrec.facade.broker.JaxWsPhysicianFacadeBroker -wsdl -r $WSDLGEN_DIR -s $WSDLGEN_DIR -d $WSDLGEN_DIR -inlineSchemas
java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR com.sun.tools.ws.WsGen com.oracle.medrec.facade.broker.JaxWsRecordFacadeBroker -wsdl -r $WSDLGEN_DIR -s $WSDLGEN_DIR -d $WSDLGEN_DIR -inlineSchemas


echo "WSDL generated in $WSDLGEN_DIR folder"

cp -r $WSDLGEN_DIR/** ./src/main/java

java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR com.sun.tools.ws.WsImport -d $SERVICESGEN_DIR -keep $WSDLGEN_DIR/PatientFacadeService.wsdl
java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR com.sun.tools.ws.WsImport -d $SERVICESGEN_DIR -keep $WSDLGEN_DIR/PhysicianFacadeService.wsdl
java $JAVA_OPTS -cp $CLASSPATH:$COMPILED_DIR com.sun.tools.ws.WsImport -d $SERVICESGEN_DIR -keep $WSDLGEN_DIR/RecordFacadeService.wsdl
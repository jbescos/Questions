#!/bin/bash

export JAVA_HOME=/home/jbescos/workspace/weblogic/depot/dev/auto_download/x86_64/jdk180351b10/jdk1.8.0_351
export PATH=$JAVA_HOME/bin:$PATH

TEST_CLASS=${1}

WLS_DEV="/home/jbescos/workspace/weblogic/depot/dev/src141200"
DOMAIN="$WLS_DEV/webservices/itest/target/wsDomain"
MIDDLEWARE="$WLS_DEV/webservices/itest/install-server/target/middleware"
TESTS="$WLS_DEV/webservices/itest/target/utp/buildout/functional/ws"

CLASSPATH=test-lib/*:${TESTS}:${MIDDLEWARE}/wlserver/server/lib/weblogic.jar
JAVA_OPTS='-Dhttps.truststore.pass=changeit -Dsun.java.launcher=SUN_STANDARD -Dsaml11wss10.ep.url=http://jbescos-PORTEGE-X40-K:6363/OWSMWSSaml11Wss10PolicyService/OWSMWSSaml11Wss10PolicyService -Dsaml.ep.url=http://jbescos-PORTEGE-X40-K:6363/WLSWSPolicyService/WLSWSPolicyService -Dx509wss10.ep.url=http://jbescos-PORTEGE-X40-K:6363/OWSMWSX509Wss10PolicyService/OWSMWSX509Wss10PolicyService -Dhttps.truststore=/home/jbescos/workspace/weblogic/depot/dev/src141200/webservices/itest/install-server/target/middleware/wlserver/server/lib/cacerts -Duser.name=jbescos -Dfoo=bar -Dappurl=http://jbescos-PORTEGE-X40-K:6363 -Drun.output.dir=/home/jbescos/workspace/weblogic/depot/dev/src141200/webservices/itest/target/utp/testout/functional/ws -Dsign.key.alias=oraclientkey -Dhttps.keystore.pass=welcome -Denc.key.pass=orakey -Dadmin.user=weblogic -Dsaml.wsdl.url=http://jbescos-PORTEGE-X40-K:6363/WLSWSPolicyService/WLSWSPolicyService?WSDL -DPASSWORD=gumby1234 -Denc.key.alias=oraclientkey -Dsun.jnu.encoding=ISO-8859-1 -Dkeystore.location=/home/jbescos/workspace/weblogic/depot/dev/src141200/webservices/itest/target/wsDomain/server-keystore.jks -Dkeystore.type=JKS -Dsaml11wss11.ep.url=http://jbescos-PORTEGE-X40-K:6363/OWSMWSSaml11Wss11PolicyService/OWSMWSSaml11Wss11PolicyService -Dawt.toolkit=sun.awt.X11.XToolkit -Dkeystore.pass=welcome -Dhttps.keystore=/home/jbescos/workspace/weblogic/depot/dev/src141200/webservices/itest/target/wsDomain/server-keystore.jks -Dorg.testlogic.testrunner.remote.ReportingService.url=rmi://[0:0:0:0:0:0:0:1%lo]:39865/ReportingSession/R3552 -Dsaml1.wsdl.url=http://jbescos-PORTEGE-X40-K:6363/OWSMWSPolicyService/OWSMWSPolicyService?WSDL -Dutmsgprotwss10.ep.url=http://jbescos-PORTEGE-X40-K:6363/OWSMWSUTMsgProtWss10PolicyService/OWSMWSUTMsgProtWss10PolicyService -Dx509wss11.ep.url=http://jbescos-PORTEGE-X40-K:6363/OWSMWSX509Wss11PolicyService/OWSMWSX509Wss11PolicyService -Dkeystore.loc=/home/jbescos/workspace/weblogic/depot/dev/src141200/webservices/itest/target/wsDomain/client-keystore.jks' 

echo "Running test $TEST_CLASS"
echo "CLASSPATH=$CLASSPATH"

java $JAVA_OPTS -cp $CLASSPATH org.junit.runner.JUnitCore $TEST_CLASS


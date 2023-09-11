#!/bin/bash

export JAVA_HOME=~/programs/java/jdk-11.0.16.1
export PATH=$JAVA_HOME/bin:$PATH
JVM_ARGS=""

mvn clean install
java $JVM_ARGS -cp target/lib/*:target/jaxb-test.jar com.sun.tools.jxc.SchemaGenerator -d target src/main/java/com/example/jaxb/Foo.java
#java $JVM_ARGS -cp target/lib/*:target/jaxb-test.jar com.example.jaxb.Main

#java $JVM_ARGS -cp target/lib/*:target/jaxb-test.jar com.sun.tools.jxc.SchemaGenerator -d target src/main/java/package1/AddressInformation.java src/main/java/package1/IssueWithPackages.java src/main/java/package1/Phone.java src/main/java/package2/AddressInformation.java
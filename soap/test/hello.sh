#!/bin/bash

curl --noproxy "*" http://localhost:7001/soap/HelloWorldWebService?WSDL

curl --noproxy "*" -v -u weblogic:weblogic123 -i -X POST --header "Content-Type: text/xml;charset=UTF-8" --data '<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns1:getString xmlns:ns1="http://ws.tododev.es/"><arg0>Test example</arg0></ns1:getString></soap:Body></soap:Envelope>' http://localhost:7001/soap/HelloWorldWebService

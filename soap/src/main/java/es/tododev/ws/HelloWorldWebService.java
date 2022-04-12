package es.tododev.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;

//@Addressing(required = false)
@WebService(name = "HelloWorldPortType", serviceName = "HelloWorldWebService")
public class HelloWorldWebService {

    @WebMethod
    public String getString(String input) {
        return "Hello " + input;
    }

}

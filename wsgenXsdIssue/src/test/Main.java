package test;

import com.sun.tools.ws.WsGen;

public class Main {

    public static void main(String[] args) throws Throwable {
        WsGen.main(new String[] {"test.ProviderMaintenanceServiceStronglyTypedEjb", "-wsdl", "-r", "wsGenGenerated", "-s", "wsGenGenerated", "-d", "wsGenGenerated"});
    }

}

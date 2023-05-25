package test;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.RequestWrapper;

@WebService(name = "ProviderMaintenanceServiceStronglyTypedType",
        serviceName="ProviderMaintenanceServiceStronglyTyped",
        targetNamespace="http://test.com")

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
        use = SOAPBinding.Use.LITERAL,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ProviderMaintenanceServiceStronglyTypedEjb {

    @WebMethod()
    @RequestWrapper(localName="createSupplier", className="test.ProviderMaintenanceServiceStronglyTypedCreateSupplierBean")
    @WebResult(name="responseInfo")
    public test.ProviderMaintenanceResponseInfo createSupplier(@WebParam(name="supplier") test.Supplier supplier, @WebParam(name="asOfDate") java.lang.String asOfDate)  {

        try {

            return new test.ProviderMaintenanceResponseInfo();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
    }

}

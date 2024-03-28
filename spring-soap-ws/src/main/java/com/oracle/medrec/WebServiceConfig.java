package com.oracle.medrec;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.RecordFacade;
import com.oracle.medrec.facade.impl.PatientFacadeImpl;
import com.oracle.medrec.facade.impl.PhysicianFacadeImpl;
import com.oracle.medrec.facade.impl.RecordFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    // http://localhost:8080/spring-soap-ws-1.0/webservices/PatientFacadeService.wsdl
    @Bean(name = "PatientFacadeService")
    public DefaultWsdl11Definition patientEndpoint(XsdSchema patientSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PatientPort");
        wsdl11Definition.setLocationUri("/webservices");
        wsdl11Definition.setTargetNamespace("http://www.oracle.com/medrec");
        wsdl11Definition.setSchema(patientSchema);
        return wsdl11Definition;
    }

    @Bean(name = "PhysicianFacadeService")
    public DefaultWsdl11Definition physicianEndpoint(XsdSchema physicianSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PhysicianPort");
        wsdl11Definition.setLocationUri("/webservices");
        wsdl11Definition.setTargetNamespace("http://www.oracle.com/medrec");
        wsdl11Definition.setSchema(physicianSchema);
        return wsdl11Definition;
    }

    @Bean(name = "RecordFacadeService")
    public DefaultWsdl11Definition recordEndpoint(XsdSchema recordSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RecordPort");
        wsdl11Definition.setLocationUri("/webservices");
        wsdl11Definition.setTargetNamespace("http://www.oracle.com/medrec");
        wsdl11Definition.setSchema(recordSchema);
        return wsdl11Definition;
    }

    @Bean(name = "patientFacade")
    public PatientFacade patientFacade() {
        return new PatientFacadeImpl();
    }

    @Bean(name = "physicianFacade")
    public PhysicianFacade physicianFacade() {
        return new PhysicianFacadeImpl();
    }

    @Bean(name = "recordFacade")
    public RecordFacade recordFacade() {
        return new RecordFacadeImpl();
    }

    @Bean
    public XsdSchema patientSchema() {
        return new SimpleXsdSchema(new ClassPathResource("PatientFacadeService_schema1.xsd"));
    }

    @Bean
    public XsdSchema physicianSchema() {
        return new SimpleXsdSchema(new ClassPathResource("PhysicianFacadeService_schema1.xsd"));
    }

    @Bean
    public XsdSchema recordSchema() {
        return new SimpleXsdSchema(new ClassPathResource("RecordFacadeService_schema1.xsd"));
    }
}

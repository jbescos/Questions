package com.oracle.medrec.facade.broker;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Endpoint
@WebService(name = "PatientFacade", portName = "PatientFacadePort",
        serviceName = "PatientFacadeService", targetNamespace = "http://www.oracle.com/medrec")
public class JaxWsPatientFacadeBroker {

    @Autowired
    private PatientFacade patientFacade;

    @WebMethod(exclude=true)
    public void setPatientFacade(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    @WebMethod
    public Patient getPatient(Long patientId) {
        return patientFacade.getPatient(patientId);
    }

    @WebMethod
    public FoundPatient findApprovedPatientBySsn(String ssn) {
        return patientFacade.findApprovedPatientBySsn(ssn);
    }

    @WebMethod
    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        return patientFacade.findApprovedPatientsByLastName(lastName);
    }
}

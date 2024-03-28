package com.oracle.medrec.facade.broker;

import java.util.List;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.broker.jaxws.FindApprovedPatientBySsn;
import com.oracle.medrec.facade.broker.jaxws.FindApprovedPatientBySsnResponse;
import com.oracle.medrec.facade.broker.jaxws.FindApprovedPatientsByLastName;
import com.oracle.medrec.facade.broker.jaxws.FindApprovedPatientsByLastNameResponse;
import com.oracle.medrec.facade.broker.jaxws.GetPatient;
import com.oracle.medrec.facade.broker.jaxws.GetPatientResponse;
import com.oracle.medrec.facade.model.FoundPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PatientEndpoint {

    private static final String NAMESPACE_URI = "http://www.oracle.com/medrec";

    @Autowired
    private PatientFacade patientFacade;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPatient")
    @ResponsePayload
    public GetPatientResponse getPatient(@RequestPayload GetPatient patient) {
        GetPatientResponse response = new GetPatientResponse();
        response.setReturn(patientFacade.getPatient(patient.getArg0()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findApprovedPatientBySsn")
    @ResponsePayload
    public FindApprovedPatientBySsnResponse findApprovedPatientBySsn(@RequestPayload FindApprovedPatientBySsn ssn) {
        FoundPatient foundPatient = patientFacade.findApprovedPatientBySsn(ssn.getArg0());
        FindApprovedPatientBySsnResponse response = new FindApprovedPatientBySsnResponse();
        response.setReturn(foundPatient);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findApprovedPatientsByLastName")
    @ResponsePayload
    public FindApprovedPatientsByLastNameResponse findApprovedPatientsByLastName(@RequestPayload FindApprovedPatientsByLastName lastName) {
        List<FoundPatient> list = patientFacade.findApprovedPatientsByLastName(lastName.getArg0());
        FindApprovedPatientsByLastNameResponse response = new FindApprovedPatientsByLastNameResponse();
        response.setReturn(list);
        return response;
    }
}

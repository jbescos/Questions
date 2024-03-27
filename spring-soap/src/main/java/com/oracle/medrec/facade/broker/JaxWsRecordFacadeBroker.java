package com.oracle.medrec.facade.broker;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import com.oracle.medrec.facade.RecordFacade;
import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@WebService(name = "RecordFacade", portName = "RecordFacadePort",
        serviceName = "RecordFacadeService", targetNamespace = "http://www.oracle.com/medrec")
public class JaxWsRecordFacadeBroker {

    @Autowired
    private RecordFacade recordFacade;

    @WebMethod(exclude=true)
    public void setRecordFacade(RecordFacade recordFacade) {
        this.recordFacade = recordFacade;
    }

    @WebMethod
    public RecordSummary getRecordSummaryByPatientId(Long patientId) {
        return recordFacade.getRecordSummaryByPatientId(patientId);
    }

    @WebMethod
    public RecordDetail getRecordDetail(Long id) {
        return recordFacade.getRecordDetail(id);
    }

    @WebMethod
    public void createRecord(RecordToCreate recordToCreate) {
        recordFacade.createRecord(recordToCreate);
    }
}

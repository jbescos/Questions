package com.oracle.medrec.facade.broker;

import com.oracle.medrec.facade.RecordFacade;
import com.oracle.medrec.facade.broker.jaxws.CreateRecord;
import com.oracle.medrec.facade.broker.jaxws.GetRecordDetail;
import com.oracle.medrec.facade.broker.jaxws.GetRecordDetailResponse;
import com.oracle.medrec.facade.broker.jaxws.GetRecordSummaryByPatientId;
import com.oracle.medrec.facade.broker.jaxws.GetRecordSummaryByPatientIdResponse;
import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Endpoint
public class RecordEndpoint {

    private static final String NAMESPACE_URI = "http://www.oracle.com/medrec";

    @Autowired
    private RecordFacade recordFacade;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRecordSummaryByPatientId")
    @ResponsePayload
    public GetRecordSummaryByPatientIdResponse getRecordSummaryByPatientId(@RequestPayload GetRecordSummaryByPatientId patientId) {
        RecordSummary recordSummary = recordFacade.getRecordSummaryByPatientId(patientId.getArg0());
        GetRecordSummaryByPatientIdResponse response = new GetRecordSummaryByPatientIdResponse();
        response.setReturn(recordSummary);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRecordDetail")
    @ResponsePayload
    public GetRecordDetailResponse getRecordDetail(@RequestPayload GetRecordDetail id) {
        RecordDetail recordDetail = recordFacade.getRecordDetail(id.getArg0());
        GetRecordDetailResponse response = new GetRecordDetailResponse();
        response.setReturn(recordDetail);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createRecord")
    public void createRecord(@RequestPayload CreateRecord recordToCreate) {
        recordFacade.createRecord(recordToCreate.getArg0());
    }
}

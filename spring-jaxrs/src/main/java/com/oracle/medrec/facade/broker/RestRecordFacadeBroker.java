package com.oracle.medrec.facade.broker;

import com.oracle.medrec.facade.RecordFacade;
import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@RestController
@RequestMapping("/RecordFacade")
public class RestRecordFacadeBroker {

    @Autowired
    private RecordFacade recordFacade;

    @GetMapping(value = "/getRecordSummaryByPatientId/{patientId}", produces = "application/json")
    public RecordSummary getRecordSummaryByPatientId(@PathVariable Long patientId) {
        return recordFacade.getRecordSummaryByPatientId(patientId);
    }

    @GetMapping(value = "/getRecordDetail/{id}", produces = "application/json")
    public RecordDetail getRecordDetail(@PathVariable Long id) {
        return recordFacade.getRecordDetail(id);
    }

    @PostMapping(value = "/createRecord", consumes = "application/json")
    public void createRecord(@RequestBody RecordToCreate recordToCreate) {
        recordFacade.createRecord(recordToCreate);
    }
}

package com.oracle.medrec.facade.impl;

import java.util.Collections;

import com.oracle.medrec.facade.RecordFacade;
import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;
import org.springframework.stereotype.Service;

@Service
public class RecordFacadeImpl implements RecordFacade {

    @Override
    public void createRecord(RecordToCreate record) {
    }

    @Override
    public RecordSummary getRecordSummaryByPatientId(Long patientId) {
        RecordSummary summary = new RecordSummary(patientId, Collections.emptyList());
        return null;
    }

    @Override
    public RecordDetail getRecordDetail(Long id) {
        RecordDetail detail = new RecordDetail();
        detail.setId(id);
        return detail;
    }

}

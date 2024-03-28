package com.oracle.medrec.facade.impl;

import com.oracle.medrec.facade.RecordFacade;
import com.oracle.medrec.facade.model.RecordDetail;
import com.oracle.medrec.facade.model.RecordSummary;
import com.oracle.medrec.facade.model.RecordToCreate;
import org.springframework.stereotype.Service;

@Service
public class RecordFacadeImpl implements RecordFacade {

    @Override
    public void createRecord(RecordToCreate record) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public RecordSummary getRecordSummaryByPatientId(Long patientId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RecordDetail getRecordDetail(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}

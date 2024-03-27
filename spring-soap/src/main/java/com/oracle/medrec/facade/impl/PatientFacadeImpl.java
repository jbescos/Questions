package com.oracle.medrec.facade.impl;

import java.util.List;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientFacadeImpl implements PatientFacade {

    @Override
    public Patient getPatient(Long patientId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FoundPatient findApprovedPatientBySsn(String ssn) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        // TODO Auto-generated method stub
        return null;
    }

}

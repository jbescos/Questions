package com.oracle.medrec.facade.impl;

import java.util.Collections;
import java.util.List;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientFacadeImpl implements PatientFacade {

    @Override
    public Patient getPatient(Long patientId) {
        Patient patient = new Patient();
        patient.setEmail("test@test.com");
        return patient;
    }

    @Override
    public FoundPatient findApprovedPatientBySsn(String ssn) {
        FoundPatient patient = new FoundPatient();
        patient.setId(8888L);
        return patient;
    }

    @Override
    public List<FoundPatient> findApprovedPatientsByLastName(String lastName) {
        return Collections.emptyList();
    }

}

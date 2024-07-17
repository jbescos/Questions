package com.oracle.medrec.facade.broker;

import java.util.List;

import com.oracle.medrec.facade.PatientFacade;
import com.oracle.medrec.facade.model.FoundPatient;
import com.oracle.medrec.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@RestController
@RequestMapping("/PatientFacade")
public class RestPatientFacadeBroker {

    @Autowired
    private PatientFacade patientFacade;

    @GetMapping(value = "/getPatient/{patientId}", produces = "application/json")
    public Patient getPatient(@PathVariable Long patientId) {
        return patientFacade.getPatient(patientId);
    }

    @GetMapping(value = "/findApprovedPatientBySsn/{ssn}", produces = "application/json")
    public FoundPatient findApprovedPatientBySsn(@PathVariable String ssn) {
        return patientFacade.findApprovedPatientBySsn(ssn);
    }

    @GetMapping(value = "/findApprovedPatientsByLastName/{lastName}", produces = "application/json")
    public List<FoundPatient> findApprovedPatientsByLastName(@PathVariable String lastName) {
        return patientFacade.findApprovedPatientsByLastName(lastName);
    }
}

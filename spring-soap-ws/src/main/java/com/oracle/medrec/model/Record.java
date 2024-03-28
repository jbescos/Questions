package com.oracle.medrec.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Entity
@Table(name = "records")
@NamedQuery(name = "Record.findRecordsByPatientId",
        query = "SELECT r FROM Record r WHERE r.patient.id = :patientId")
public class Record extends VersionedEntity {

    private static final long serialVersionUID = -4395051789276646078L;

    private String diagnosis;

    private String notes;

    @jakarta.persistence.Temporal(jakarta.persistence.TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date date;

    private String symptoms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Physician physician;

    private VitalSigns vitalSigns;

    /**
     * Prescriptions need to be acessed whenever record is accessed
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Prescription> prescriptions = new LinkedList<Prescription>();

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        System.out.println("*** Diagnosis = "+diagnosis);
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        System.out.println("*** Notes = "+notes);
        this.notes = notes;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        System.out.println("*** Physician = "+physician);
        this.physician = physician;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        if (prescriptions != null) {
            for (int i=0; i< prescriptions.size(); i++) {
                System.out.println("*** Prescription["+i+"] = "+prescriptions.get(i));
            }
        }
        this.prescriptions = prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        System.out.println("*** addPrescription = "+prescription);
        prescriptions.add(prescription);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        System.out.println("*** Date = "+date);
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        System.out.println("*** Symptoms = "+symptoms);
        this.symptoms = symptoms;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        System.out.println("*** VitalSigns = "+vitalSigns.toString());
        this.vitalSigns = vitalSigns;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        System.out.println("*** Patient = "+patient.toString());
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Record record = (Record) o;

        if (date != null ? !date.equals(record.getDate()) : record.getDate() != null) {
            return false;
        }
        if (patient != null ? !patient.equals(record.getPatient()) : record.getPatient() != null) {
            return false;
        }
        if (physician != null ? !physician.equals(record.getPhysician()) : record.getPhysician() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (date != null ? date.hashCode() : 0);
        result = 29 * result + (patient != null ? patient.hashCode() : 0);
        result = 29 * result + (physician != null ? physician.hashCode() : 0);
        return result;
    }
}

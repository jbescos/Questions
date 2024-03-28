package com.oracle.medrec.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Entity
@Table(name = "patients"/*, uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "username", "ssn"})}*/)
@NamedQueries({
@NamedQuery(name = "Patient.countPatientByUsernameAndPasswordAndStatus",
        query = "SELECT COUNT(p) FROM Patient p WHERE p.username = :username AND p.password = :password AND p.status = :status"),
@NamedQuery(name = "Patient.countPatientByUsernameAndPassword",
        query = "SELECT COUNT(p) FROM Patient p WHERE p.username = :username AND p.password = :password"),
@NamedQuery(name = "Patient.countPatientByUsername",
        query = "SELECT COUNT(p) FROM Patient p WHERE p.username = :username"),
@NamedQuery(name = "Patient.countPatientBySsnAndId",
        query = "SELECT COUNT(p) FROM Patient p WHERE p.ssn = :ssn AND p.id <> :id"),
@NamedQuery(name = "Patient.countPatientBySsn",
        query = "SELECT COUNT(p) FROM Patient p WHERE p.ssn = :ssn"),
@NamedQuery(name = "Patient.findPatientByStatus",
        query = "SELECT p FROM Patient p WHERE p.status = :status"),
@NamedQuery(name = "Patient.findPatientBySsnAndStatus",
        query = "SELECT p FROM Patient p WHERE p.ssn = :ssn AND p.status = :status"),
@NamedQuery(name = "Patient.findPatientByLastNameAndStatus",
        query = "SELECT p FROM Patient p WHERE p.name.lastName = :lastName AND p.status = :status"),
@NamedQuery(name = "Patient.findPatientByUsernameAndPasswordAndStatus",
        query = "SELECT p FROM Patient p WHERE p.username = :username AND p.password = :password AND p.status = :status"),
@NamedQuery(name = "Patient.findPatientByUsernameAndPassword",
        query = "SELECT p FROM Patient p WHERE p.username = :username AND p.password = :password")
        })
public class Patient extends RegularUser {

    private static final long serialVersionUID = 313728838021028177L;

    @jakarta.persistence.Temporal(jakarta.persistence.TemporalType.DATE)
    private Date dob;
  
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String ssn;

    private Address address = new Address();

    @Enumerated(EnumType.STRING)
    private Patient.Status status = Patient.Status.REGISTERED;

    // No setter and getter... Now used to do cascading
    @OneToMany(cascade = CascadeType.ALL)
    private List<Record> records;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void approve() {
        setStatus(Patient.Status.APPROVED);
    }

    public void deny() {
        setStatus(Patient.Status.DENIED);
    }

    public boolean isApproved() {
        return Patient.Status.APPROVED.equals(getStatus());
    }

    public boolean isDenied() {
        return Patient.Status.DENIED.equals(getStatus());
    }

    public Patient.Status getStatus() {
        return status;
    }

    public void setStatus(Patient.Status status) {
        this.status = status;
    }

    public enum Status {
        REGISTERED, APPROVED, DENIED
    }

    public enum Gender {
        MALE, FEMALE
    }

    public String toString() {
        return "Patient["+String.join(" ", super.toString(), gender != null ? gender.toString(): null, ssn, address != null ? address.toString() : null, status != null ? status.toString() : null)+"]";
    }
}

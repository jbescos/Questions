package com.oracle.medrec.facade.model;

import com.oracle.medrec.model.PersonName;
import com.oracle.medrec.model.Record;

import java.util.Date;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
public class SummarizedRecord extends TransferObject {

    private static final long serialVersionUID = 897453345L;

    private Long id;

    private Date date;

    private String symptoms;

    private PersonName physicianName;

    public SummarizedRecord() {
    }

    public SummarizedRecord(Record record) {
        id = record.getId();
        symptoms = record.getSymptoms();
        date = record.getDate();
        physicianName = record.getPhysician().getName();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public PersonName getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(PersonName physicianName) {
        this.physicianName = physicianName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!id.equals(((SummarizedRecord) object).getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

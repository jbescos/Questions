package com.oracle.medrec.model;

import jakarta.persistence.MappedSuperclass;

/**
 * Base class defining properties common to any regular users (not including administrators).
 *
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights reserved.
 */
@MappedSuperclass
public abstract class RegularUser extends User {

    private PersonName name = new PersonName();

    private String phone;

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

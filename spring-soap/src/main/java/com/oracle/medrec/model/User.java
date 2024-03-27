package com.oracle.medrec.model;

import jakarta.persistence.MappedSuperclass;

/**
 * Base class defining the stuff common to any types of users.
 *
 * In current MedRec, each user only has one role identified by its class type.
 *
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights reserved.
 */
@MappedSuperclass
public abstract class User extends VersionedEntity {

    private String username;

    private String password;

    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        setUsername(email);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        User user = (User) object;

        if (username != null ? !username.equals(user.getUsername()) : user.getUsername() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

}

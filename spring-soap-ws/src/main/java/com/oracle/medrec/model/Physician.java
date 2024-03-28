package com.oracle.medrec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Entity
@Table(name = "physicians"/*, uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "username"})}*/)
@NamedQueries({
    @NamedQuery(name = "Physician.findPhysicianByUsernameAndPassword",
            query = "SELECT p FROM Physician p WHERE p.username = :username AND p.password = :password"),
    @NamedQuery(name = "Physician.countPhysicianByUsernameAndPassword",
            query = "SELECT COUNT(p) FROM Physician p WHERE p.username = :username AND p.password = :password"),
    @NamedQuery(name = "Physician.countPhysicianByUsername",
            query = "SELECT COUNT(p) FROM Physician p WHERE p.username = :username")
})
public class Physician extends RegularUser {

    private static final long serialVersionUID = 2298019356529544739L;

}

package com.oracle.medrec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * @author Copyright (c) 2007,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Entity
@Table(name = "administrators"/*, uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "username"})}*/)
@NamedQueries({
    @NamedQuery(name = "Administrator.countAdministratorByUsernameAndPassword",
            query = "SELECT COUNT(a) FROM Administrator a WHERE a.username = :username AND a.password = :password"),
    @NamedQuery(name = "Administrator.findAdministratorByUsernameAndPassword",
            query = "SELECT a FROM Administrator a WHERE a.username = :username AND a.password = :password"),    
    @NamedQuery(name = "Administrator.countAdministratorByUsername",
            query = "SELECT COUNT(a) FROM Administrator a WHERE a.username = :username")
})
public class Administrator extends User {

    private static final long serialVersionUID = -6565765035454590497L;
}

package com.oracle.medrec.facade.broker;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@WebService(name = "PhysicianFacade", portName = "PhysicianFacadePort",
        serviceName = "PhysicianFacadeService", targetNamespace = "http://www.oracle.com/medrec")
public class JaxWsPhysicianFacadeBroker {

    @Autowired
    private PhysicianFacade physicianFacade;

    @WebMethod(exclude=true)
    public void setPhysicianFacade(PhysicianFacade physicianFacade) {
        this.physicianFacade = physicianFacade;
    }

    @WebMethod
    public boolean authenticatePhysician(String username, String password) {
        return physicianFacade.authenticatePhysician(username, password);
    }

    @WebMethod
    public AuthenticatedPhysician authenticateAndReturnPhysician(String username, String password) {
        return physicianFacade.authenticateAndReturnPhysician(username, password);
    }
}

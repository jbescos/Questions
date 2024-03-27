package com.oracle.medrec.facade.broker;

import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.broker.jaxws.AuthenticateAndReturnPhysician;
import com.oracle.medrec.facade.broker.jaxws.AuthenticateAndReturnPhysicianResponse;
import com.oracle.medrec.facade.broker.jaxws.AuthenticatePhysician;
import com.oracle.medrec.facade.broker.jaxws.AuthenticatePhysicianResponse;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@Endpoint
public class PhysicianEndpoint {

    private static final String NAMESPACE_URI = "http://www.oracle.com/medrec";

    @Autowired
    private PhysicianFacade physicianFacade;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "authenticatePhysician")
    @ResponsePayload
    public AuthenticatePhysicianResponse authenticatePhysician(@RequestPayload AuthenticatePhysician authenticatePhysician) {
        boolean bool = physicianFacade.authenticatePhysician(authenticatePhysician.getArg0(), authenticatePhysician.getArg1());
        AuthenticatePhysicianResponse response = new AuthenticatePhysicianResponse();
        response.setReturn(bool);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "authenticateAndReturnPhysician")
    @ResponsePayload
    public AuthenticateAndReturnPhysicianResponse authenticateAndReturnPhysician(@RequestPayload AuthenticateAndReturnPhysician authenticateAndReturnPhysician) {
        AuthenticatedPhysician authenticatedPhysician = physicianFacade.authenticateAndReturnPhysician(authenticateAndReturnPhysician.getArg0(), authenticateAndReturnPhysician.getArg1());
        AuthenticateAndReturnPhysicianResponse response = new AuthenticateAndReturnPhysicianResponse();
        response.setReturn(authenticatedPhysician);
        return response;
    }
}

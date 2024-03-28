package com.oracle.medrec.facade;

import com.oracle.medrec.facade.model.AuthenticatedPhysician;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
public interface PhysicianFacade {

    boolean authenticatePhysician(String username, String password);

    AuthenticatedPhysician authenticateAndReturnPhysician(String username, String password);
}

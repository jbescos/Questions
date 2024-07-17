package com.oracle.medrec.facade.impl;

import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import org.springframework.stereotype.Service;

@Service
public class PhysicianFacadeImpl implements PhysicianFacade {

    @Override
    public boolean authenticatePhysician(String username, String password) {
        return true;
    }

    @Override
    public AuthenticatedPhysician authenticateAndReturnPhysician(String username, String password) {
        AuthenticatedPhysician a = new AuthenticatedPhysician();
        a.setUsername(username);
        return a;
    }

}

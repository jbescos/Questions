package com.oracle.medrec.facade.broker;

import com.oracle.medrec.facade.PhysicianFacade;
import com.oracle.medrec.facade.model.AuthenticatedPhysician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 */
@RestController
@RequestMapping("/PhysicianFacade")
public class RestPhysicianFacadeBroker {

    @Autowired
    private PhysicianFacade physicianFacade;

    @GetMapping(value = "/authenticatePhysician", produces = "application/json")
    public boolean authenticatePhysician(@RequestParam String username, @RequestParam String password) {
        return physicianFacade.authenticatePhysician(username, password);
    }

    @GetMapping(value = "/authenticateAndReturnPhysician", produces = "application/json")
    public AuthenticatedPhysician authenticateAndReturnPhysician(@RequestParam String username, @RequestParam String password) {
        return physicianFacade.authenticateAndReturnPhysician(username, password);
    }
}

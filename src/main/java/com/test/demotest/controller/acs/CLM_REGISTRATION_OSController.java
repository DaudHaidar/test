package com.test.demotest.controller.acs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;
import com.test.demotest.service.acs.CLM_REGISTRATION_OSService;

@RestController
public class CLM_REGISTRATION_OSController {
    @Autowired
    private CLM_REGISTRATION_OSService cRegistrationService;

    @GetMapping("/api/v1/shs/subro/registrationOs/{registrationId}")
    public CLM_REGISTRATION_OS findByRegistration(@PathVariable("registrationId") String registrationId){
        return cRegistrationService.findByRegistrationId(registrationId);
    }
    
}

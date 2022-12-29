package com.askrindo.aossubrogasi.controller.acs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.askrindo.aossubrogasi.entity.acs.CLM_REGISTRATION_OS;
import com.askrindo.aossubrogasi.service.acs.CLM_REGISTRATION_OSService;

@RestController
public class CLM_REGISTRATION_OSController {
    @Autowired
    private CLM_REGISTRATION_OSService cRegistrationService;

    @GetMapping("/api/v1/shs/subro/registrationOs/{registrationId}")
    public List<CLM_REGISTRATION_OS>findByRegistration(@PathVariable("registrationId") String registrationId){
        return cRegistrationService.findByRegistrationId(registrationId);
    }
    
}

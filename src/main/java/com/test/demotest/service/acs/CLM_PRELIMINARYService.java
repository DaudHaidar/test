package com.test.demotest.service.acs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_PRELIMINARY;
import com.test.demotest.repository.acs.CLM_PRELIMINARYRepository;

@Service
public class CLM_PRELIMINARYService {
    
    @Autowired
    private CLM_PRELIMINARYRepository clmPreliminaryRepository;

    public CLM_PRELIMINARY findRegistrationId(String registrationId){
        try {
            return clmPreliminaryRepository.findByRegistrationId(registrationId);
        } catch (Exception e) {
            throw new RuntimeException("RegistrationId tidak ditemukan");
        }

    }
}

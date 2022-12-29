package com.askrindo.aossubrogasi.service.acs;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askrindo.aossubrogasi.entity.acs.CLM_PRELIMINARY;
import com.askrindo.aossubrogasi.repository.acs.CLM_PRELIMINARYRepository;

@Service
@Transactional
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

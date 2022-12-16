package com.test.demotest.service.acs;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;
import com.test.demotest.repository.acs.CLM_REGISTRATION_OSRepository;

@Service
@Transactional
public class CLM_REGISTRATION_OSService {
    @Autowired
    private CLM_REGISTRATION_OSRepository registrationOSRepository;

    public CLM_REGISTRATION_OS update(String registrationId,  Double amtOs, Double amtSettled ){

        if(registrationOSRepository.findById(registrationId)==null){
            throw new RuntimeException("id tidak ditemukan");
        }

        CLM_REGISTRATION_OS registrationOsUpdate = registrationOSRepository.findByRegistrationId(registrationId);
        registrationOsUpdate.setRegistrationId(registrationId);
        registrationOsUpdate.setAmtOs(amtOs);
        registrationOsUpdate.setAmtSettled(amtSettled);


        return registrationOSRepository.save(registrationOsUpdate);
    }

    public CLM_REGISTRATION_OS findByRegistrationId(String registrationId){
        return registrationOSRepository.findByRegistrationId(registrationId);
    }

}

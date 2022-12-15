package com.test.demotest.service.acs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;
import com.test.demotest.repository.acs.CLM_REGISTRATION_OSRepository;


@Service
public class CLM_REGISTRATION_OSService {
    @Autowired
    private CLM_REGISTRATION_OSRepository registrationOSRepository;

    public CLM_REGISTRATION_OS update(CLM_REGISTRATION_OS registrationOs, String registrationId, String currency,String clmOsType, String clmsStatus, Double amtOs,Double amtLoss, Double amtSettled,Double idrRate ){
        if(registrationOSRepository.findById(registrationId)==null){
            throw new RuntimeException("id tidak ditemukan");
        }
        CLM_REGISTRATION_OS registrationOsUpdate = registrationOSRepository.findById(registrationId).get();
        registrationOsUpdate.setCurrencyCode(currency);
        registrationOs.setClmOstype(clmOsType);
        registrationOs.setClmStatus(clmsStatus);
        registrationOs.setAmtOs(amtOs);
        registrationOs.setAmtLoss(amtLoss);
        registrationOs.setAmtSettled(amtSettled);
        registrationOs.setIdrRate(idrRate);

        return registrationOSRepository.save(registrationOsUpdate);
    }
}

package com.test.demotest.service.acs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;
import com.test.demotest.repository.acs.CLM_REGISTRATION_OSRepository;

@Service
@Transactional
public class CLM_REGISTRATION_OSService {
    @Autowired
    private CLM_REGISTRATION_OSRepository registrationOSRepository;

    public CLM_REGISTRATION_OS update(CLM_INQUIRY_SUBROGATION_CREDIT cInquiry, CLM_RECOV_PAYMENT cRecovPayment){

        if(registrationOSRepository.findById(cInquiry.getRegistrationId())==null){
            throw new RuntimeException("registrationId tidak ditemukan");
        }

        // CLM_REGISTRATION_OS registrationOsUpdate = registrationOSRepository.findByRegistrationId(registrationId);

        List<CLM_REGISTRATION_OS> registrationOsList = new ArrayList<>(findByRegistrationId(cInquiry.getRegistrationId()));

        CLM_REGISTRATION_OS registrationOsFilter = registrationOsList.stream().filter(( CLM_REGISTRATION_OS clmRegist)->{

            return clmRegist.getClmOstype().equals("SUBROGATION");
        }).findFirst().get();

        Double amtOs =cRecovPayment.getAmtShsAfter();
        Double amtSettled =  cInquiry.getAmtClaimPayment()-cRecovPayment.getAmtShsAfter();
      
        registrationOsFilter.setRegistrationId(cInquiry.getRegistrationId());
        registrationOsFilter.setAmtOs(amtOs<0.0? 0.0:amtOs);
        registrationOsFilter.setAmtSettled(amtSettled);

        return registrationOSRepository.save(registrationOsFilter);
    }

    public List<CLM_REGISTRATION_OS> findByRegistrationId(String registrationId){
        return registrationOSRepository.findByRegistrationId(registrationId);
    }

}

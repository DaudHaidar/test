package com.test.demotest.service.acs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new RuntimeException("registrationId tidak ditemukan");
        }

        // CLM_REGISTRATION_OS registrationOsUpdate = registrationOSRepository.findByRegistrationId(registrationId);

        List<CLM_REGISTRATION_OS> registrationOsList = new ArrayList<>(findByRegistrationId(registrationId));

        System.out.println("registrationOsList : "+ registrationOsList);
        System.out.println("clmostype :"+registrationOsList.get(0).getClmOstype() );

        CLM_REGISTRATION_OS registrationOsFilter = registrationOsList.stream().filter(( CLM_REGISTRATION_OS clmRegist)->{
            System.out.println("CLM REGIST  :" + clmRegist);
            return clmRegist.getClmOstype().equals("SUBROGATION");
        }).findFirst().get();

        System.out.println("registrationOsFilter"+ registrationOsFilter);
      
        registrationOsFilter.setRegistrationId(registrationId);
        registrationOsFilter.setAmtOs(amtOs<0.0? 0.0:amtOs);
        registrationOsFilter.setAmtSettled(amtSettled);

        return registrationOSRepository.save(registrationOsFilter);
    }

    public List<CLM_REGISTRATION_OS> findByRegistrationId(String registrationId){
        return registrationOSRepository.findByRegistrationId(registrationId);
    }

}

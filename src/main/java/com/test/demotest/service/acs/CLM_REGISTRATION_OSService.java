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

        List<CLM_REGISTRATION_OS> registrationOsList = new ArrayList<CLM_REGISTRATION_OS>(findByRegistrationId(registrationId));

        System.out.println("registrationOsList : "+ registrationOsList);
        System.out.println("clmostype :"+registrationOsList.get(0).getClmOstype() );

        List<CLM_REGISTRATION_OS> registrationOsFilter = findByRegistrationId(registrationId).stream().filter(clmRegist->{
            System.out.println("CLM REGIST  :" + clmRegist.getClmOstype());
            return clmRegist.getClmOstype()=="SUBROGATION";
        }).collect(Collectors.toList());

        System.out.println("registrationOsFilter"+ registrationOsFilter);

        CLM_REGISTRATION_OS getregistrationOs = registrationOsFilter.get(0);
        System.out.println("getregistrationOs  : "+getregistrationOs);
        
        getregistrationOs.setRegistrationId(registrationId);
        getregistrationOs.setAmtOs(amtOs);
        getregistrationOs.setAmtSettled(amtSettled);


        return registrationOSRepository.save(getregistrationOs);
    }

    public List<CLM_REGISTRATION_OS> findByRegistrationId(String registrationId){
        return registrationOSRepository.findByRegistrationId(registrationId);
    }

}

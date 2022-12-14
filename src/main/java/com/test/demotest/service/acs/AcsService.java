package com.test.demotest.service.acs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.V_CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.repository.acs.AcsRepository;

@Service
public class AcsService {
    @Autowired
    private AcsRepository acsRepository;

    public V_CLM_INQUIRY_SUBROGATION_CREDIT getByNoRekening(String noRekening){
        try {
            return acsRepository.findClmQuery(noRekening);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        
    }
}

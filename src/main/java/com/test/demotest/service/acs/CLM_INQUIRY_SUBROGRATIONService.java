package com.test.demotest.service.acs;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.repository.acs.CLM_INQUIRY_SUBROGATIONRepository;

@Service
@Transactional
public class CLM_INQUIRY_SUBROGRATIONService {
    @Autowired
    private CLM_INQUIRY_SUBROGATIONRepository acsRepository;

    public CLM_INQUIRY_SUBROGATION_CREDIT getByNoRekening(String noRekening){
        try {
            return acsRepository.findClmQuery(noRekening);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }   
    }

    public List<CLM_INQUIRY_SUBROGATION_CREDIT> getByZeroSubrogration(Double sisa){
        try {
            return acsRepository.findClmSubrogration(sisa);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}

package com.test.demotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.Acs;
import com.test.demotest.repository.AcsRepository;

@Service
public class AcsService {
    @Autowired
    private AcsRepository acsRepository;

    public Acs getByNoRekening(String noRekening){
        return acsRepository.findClmQuery(noRekening);
    }
}

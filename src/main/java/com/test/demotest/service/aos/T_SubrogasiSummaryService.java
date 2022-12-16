package com.test.demotest.service.aos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.aos.T_Subrogasi_Summary;
import com.test.demotest.repository.aos.T_SubrogasiSummaryRepository;

@Service
@Transactional
public class T_SubrogasiSummaryService {
    @Autowired
    private T_SubrogasiSummaryRepository subrogasiSummaryRepository;

    public T_Subrogasi_Summary create(T_Subrogasi_Summary subrogasiSummary){
        return subrogasiSummaryRepository.save(subrogasiSummary);
    }

    public Iterable<T_Subrogasi_Summary> getAll(){
        return subrogasiSummaryRepository.findAll();
    }

    public T_Subrogasi_Summary update(T_Subrogasi_Summary subrogasiSummary, String id){
        if(!(subrogasiSummaryRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id:" + id+ "tidak ditemukan");
        }
        return subrogasiSummaryRepository.save(subrogasiSummary);
    }

    public T_Subrogasi_Summary getById(String id){
        if(!(subrogasiSummaryRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id:" + id+ "tidak ditemukan");
        }
        return subrogasiSummaryRepository.findById(id).get();
    }

    public void deleteById(String id){
        subrogasiSummaryRepository.deleteById(id);
    }

    public T_Subrogasi_Summary findByLineNo(Integer counterAngsuran){
        return subrogasiSummaryRepository.findByLineNo(counterAngsuran);
    }
}

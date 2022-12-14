package com.test.demotest.service.aos;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.repository.aos.T_SubrogasiRepository;


@Service
@Transactional
public class T_SubrogasiService {
    @Autowired
    private T_SubrogasiRepository subrogasiRepository;
    

    public T_Subrogasi save(T_Subrogasi subrogasi){
        return subrogasiRepository.save(subrogasi);
    }

    public T_Subrogasi update(T_Subrogasi subrogasi, String id){
        if(!(subrogasiRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id"+ id+"tidak ditemukan");
        }
        return subrogasiRepository.save(subrogasi);
    }

    public Iterable<T_Subrogasi> getAll(){
        return subrogasiRepository.findAll();
    }

    public T_Subrogasi getByIdSubrogasi(String id){
        if(!(subrogasiRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id"+ id+"tidak ditemukan");
        }
        return subrogasiRepository.findById(id).get();
    }

    public void delete(String id){
        subrogasiRepository.deleteById(id);
    }


}

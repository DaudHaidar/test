package com.test.demotest.service.aos;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.repository.aos.T_SubrogasiRepository;


@Service
@Transactional
public class T_SubrogasiService {
    @Autowired
    private T_SubrogasiRepository subrogasiRepository;

    public T_Subrogasi save(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry){

        T_Subrogasi tSubrogasi = new T_Subrogasi();

        Double akumulasiSubrogasi = cInquiry.getAmtRecovery()+request.getNilaiRecoveries();

        tSubrogasi.setNoRekening(request.getNoRekening());
        tSubrogasi.setNomorPeserta(request.getNoRekening());
        tSubrogasi.setNominalClaim(request.getNilaiRecoveries());
        tSubrogasi.setAkumulasiSubrogasi(akumulasiSubrogasi);
        tSubrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
        tSubrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
        tSubrogasi.setPresentaseCollectingFee(Double.valueOf(0));
        tSubrogasi.setPresentasePajak(Double.valueOf(0));
        tSubrogasi.setIdKlaim(UUID.randomUUID().toString());
        tSubrogasi.setStatus("0");

        return subrogasiRepository.save(tSubrogasi);
    }

    public T_Subrogasi update(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry, String id){
        if(!(subrogasiRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id"+ id+"tidak ditemukan");
        }

        T_Subrogasi updtSubrogasi = subrogasiRepository.findById(id).get();
        updtSubrogasi.setId(id);
        updtSubrogasi.setNoRekening(request.getNoRekening());
        updtSubrogasi.setNomorPeserta(request.getNoRekening());
        updtSubrogasi.setNominalClaim(request.getNilaiRecoveries());
        updtSubrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
        updtSubrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
        updtSubrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
        return subrogasiRepository.save(updtSubrogasi);
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

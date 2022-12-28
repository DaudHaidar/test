package com.test.demotest.service.aos;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.entity.aos.T_Subrogasi_Summary;
import com.test.demotest.repository.aos.T_SubrogasiRepository;


@Service
@Transactional
public class T_SubrogasiService {
    @Autowired
    private T_SubrogasiRepository subrogasiRepository;
    @Autowired
    private T_SubrogasiSummaryService subrogasiSummaryService;

    public T_Subrogasi save(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry, CLM_RECOV_PAYMENT cRecovPayment){

        Double sisaKewajibanSubrogasi ;

        if(cRecovPayment == null){
            sisaKewajibanSubrogasi = cInquiry.getAmtSubrogation();
       }else{
           sisaKewajibanSubrogasi = cRecovPayment.getAmtShsAfter();
       }

        T_Subrogasi tSubrogasi = new T_Subrogasi();

        Double akumulasiSubrogasi = cInquiry.getAmtRecovery()+request.getNilaiRecoveries();
        tSubrogasi.setId(UUID.randomUUID().toString());
        tSubrogasi.setNoRekening(request.getNoRekening());
        tSubrogasi.setNomorPeserta(request.getNoRekening());
        tSubrogasi.setNominalClaim(request.getNilaiRecoveries());
        tSubrogasi.setAkumulasiSubrogasi(akumulasiSubrogasi);
        tSubrogasi.setSisaKewajibanSubrogasi(sisaKewajibanSubrogasi);
        tSubrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
        tSubrogasi.setPresentaseCollectingFee(Double.valueOf(0));
        tSubrogasi.setPresentasePajak(Double.valueOf(0));
        tSubrogasi.setIdKlaim(UUID.randomUUID().toString());
        tSubrogasi.setStatus("0");

        return subrogasiRepository.save(tSubrogasi);
    }

    public T_Subrogasi update(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry, String id,CLM_RECOV_PAYMENT cRecovPayment){

        Double sisaKewajibanSubrogasi ;

        if(!(subrogasiRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id"+ id+"tidak ditemukan");
        }

        if(cRecovPayment == null){
            sisaKewajibanSubrogasi = cInquiry.getAmtSubrogation();
            
            
       }else{
           sisaKewajibanSubrogasi = cRecovPayment.getAmtShsAfter();
          
       }

        T_Subrogasi updtSubrogasi = subrogasiRepository.findById(id).get();
        System.out.println("updtSubro : "+ updtSubrogasi.getId());
        updtSubrogasi.setId(id);
        updtSubrogasi.setNoRekening(request.getNoRekening());
        updtSubrogasi.setNomorPeserta(request.getNoRekening());
        updtSubrogasi.setNominalClaim(request.getNilaiRecoveries());
        updtSubrogasi.setSisaKewajibanSubrogasi(sisaKewajibanSubrogasi);
        updtSubrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
        updtSubrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
        
        subrogasiRepository.save(updtSubrogasi);



        Double totalAkumulasiSubrogasi;
        Double totalNominalSubrogasiPokok;

        if(cRecovPayment == null){

            List<T_Subrogasi_Summary> getNominalSubrogasiPokok = subrogasiSummaryService.findBySubroId(id);
             
            List<T_Subrogasi_Summary> getNominalSubrogasiPokokSortedByDate = getNominalSubrogasiPokok.stream().sorted(Comparator.comparing(T_Subrogasi_Summary::getCreatedDate)).collect(Collectors.toList());
    
            T_Subrogasi_Summary getLastIndexSubroSummary = getNominalSubrogasiPokokSortedByDate.get(getNominalSubrogasiPokokSortedByDate.size()-1);
    
            totalNominalSubrogasiPokok = getNominalSubrogasiPokokSortedByDate.stream().filter(subrogasiLebih-> subrogasiLebih.getNominalSubrogasLebih()>0).mapToDouble(nominalSubroPokok -> nominalSubroPokok.getNominalSubrogasiPokok()).sum();
    
            List<T_Subrogasi_Summary >subroGetLastIndexByFilter = getNominalSubrogasiPokokSortedByDate.stream().filter(subrogasiLebih-> subrogasiLebih.getNominalSubrogasLebih()>0).collect(Collectors.toList());
    
            System.out.println("GET LASTT NDEXX POKOK :" + subroGetLastIndexByFilter);
    
            System.out.println("GET NOMINAL SUBROGASI POKOK :" + getNominalSubrogasiPokokSortedByDate);
            System.out.println("cInquiry amt recovery :" +cInquiry.getAmtRecovery());
            System.out.println("totalNominalSubrogasiPokok :" + totalNominalSubrogasiPokok);
            totalAkumulasiSubrogasi = getLastIndexSubroSummary.getSubrogasiId().getAkumulasiSubrogasi()+totalNominalSubrogasiPokok;      
            
       }else{
           totalAkumulasiSubrogasi= cInquiry.getAmtRecovery()+request.getNilaiRecoveries();
       }

        //alasan diupdate 2 kali karena table subrogasi_summary belum ke create kalau table subrogasinya belum diupdate

        T_Subrogasi updtAkumulukasiSubrogasi = subrogasiRepository.findById(id).get();
        System.out.println("updtSubro : "+ updtSubrogasi.getId());
        updtAkumulukasiSubrogasi.setId(id);
        System.out.println("request recoveries :" + request.getNilaiRecoveries());
        System.out.println("total akumulasi subrogasi pokok"+ totalAkumulasiSubrogasi);
        updtAkumulukasiSubrogasi.setAkumulasiSubrogasi(totalAkumulasiSubrogasi);

        
        return subrogasiRepository.save(updtAkumulukasiSubrogasi);

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

    public T_Subrogasi findByNoRekening(String noRekening){
        try {
            return subrogasiRepository.findByNoRekening(noRekening);
        } catch (Exception e) {
            System.out.println("error service :"+ e);
            throw new RuntimeException(e.getMessage());
        }
        
    }


}

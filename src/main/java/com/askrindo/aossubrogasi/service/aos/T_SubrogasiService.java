package com.askrindo.aossubrogasi.service.aos;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askrindo.aossubrogasi.dto.RequestSubrogasi;
import com.askrindo.aossubrogasi.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.askrindo.aossubrogasi.entity.acs.CLM_RECOV_PAYMENT;
import com.askrindo.aossubrogasi.entity.aos.T_Subrogasi;
import com.askrindo.aossubrogasi.entity.aos.T_Subrogasi_Summary;
import com.askrindo.aossubrogasi.repository.aos.T_SubrogasiRepository;


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

        // System.out.println("LastInquirySubrogation saat create :" + lastInquiryAmtRecovery(cInquiry.getAmtRecovery()));
        return subrogasiRepository.save(tSubrogasi);
    }

    public T_Subrogasi update(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry, String id,CLM_RECOV_PAYMENT cRecovPayment){

        Double sisaKewajibanSubrogasi ;

        Double totalAkumulasiSubrogasi;
        Double totalNominalSubrogasiPokok;
                
        if(!(subrogasiRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id"+ id+"tidak ditemukan");
        }

        
        T_Subrogasi updtSubrogasi = subrogasiRepository.findById(id).get();
        updtSubrogasi.setId(id);
        updtSubrogasi.setNoRekening(request.getNoRekening());
        updtSubrogasi.setNomorPeserta(request.getNoRekening());
        updtSubrogasi.setNominalClaim(request.getNilaiRecoveries());

        if(cRecovPayment == null){
            
            // List<T_Subrogasi_Summary> getNominalSubrogasiPokok = subrogasiSummaryService.findBySubroId(updtSubrogasi.getId());
             
            // List<T_Subrogasi_Summary> getNominalSubrogasiPokokSortedByDate = getNominalSubrogasiPokok.stream().sorted(Comparator.comparing(T_Subrogasi_Summary::getCreatedDate).reversed()).collect(Collectors.toList());
    
            // T_Subrogasi_Summary getLastIndexSubroSummary = getNominalSubrogasiPokokSortedByDate.get(getNominalSubrogasiPokokSortedByDate.size()-1);
            
            // List<T_Subrogasi_Summary> getSubroMoreThanZero = getNominalSubrogasiPokokSortedByDate.stream().filter(subrogasiLebih-> subrogasiLebih.getNominalSubrogasLebih()>0).collect(Collectors.toList());

            // totalNominalSubrogasiPokok = getNominalSubrogasiPokok.stream().filter(subrogasiLebih-> subrogasiLebih.getNominalSubrogasLebih()>0).mapToDouble(nominalSubroPokok -> nominalSubroPokok.getNominalSubrogasiPokok()).sum();

            // T_Subrogasi_Summary getLastIndexSubroSummaryBySubrogasiLebih= getSubroMoreThanZero.get(getSubroMoreThanZero.size()-1);

            // System.out.println("updtSubrogasi : "+updtSubrogasi.getAkumulasiSubrogasi());

            // System.out.println("totalNominalSubrogasiPokok : "+totalNominalSubrogasiPokok);

            // System.out.println("LastInquiryAmtRecovery saat update :" + lastInquiryAmtRecovery);
            
            sisaKewajibanSubrogasi = cInquiry.getAmtSubrogation();
            totalAkumulasiSubrogasi = updtSubrogasi.getAkumulasiSubrogasi()+request.getNilaiRecoveries();
       }else{
           sisaKewajibanSubrogasi = cRecovPayment.getAmtShsAfter();
           totalAkumulasiSubrogasi=cInquiry.getAmtRecovery()+request.getNilaiRecoveries();
       }

        updtSubrogasi.setSisaKewajibanSubrogasi(sisaKewajibanSubrogasi);
        updtSubrogasi.setAkumulasiSubrogasi(totalAkumulasiSubrogasi);
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

    public T_Subrogasi findByNoRekening(String noRekening){
        try {
            return subrogasiRepository.findByNoRekening(noRekening);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
    }


}

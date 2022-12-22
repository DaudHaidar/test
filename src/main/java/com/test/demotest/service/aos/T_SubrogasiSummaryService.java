package com.test.demotest.service.aos;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.entity.aos.T_Subrogasi_Summary;
import com.test.demotest.repository.aos.T_SubrogasiSummaryRepository;

@Service
@Transactional
public class T_SubrogasiSummaryService {
    @Autowired
    private T_SubrogasiSummaryRepository subrogasiSummaryRepository;

    public T_Subrogasi_Summary save(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry,T_Subrogasi subrogasiId){

        Integer lineNomor = request.getCounterAngsuran();
        Double nominalSubrogasiLebih = request.getNilaiRecoveries()-cInquiry.getAmtSubrogation();

        T_Subrogasi_Summary subroSummary = new T_Subrogasi_Summary();
        subroSummary.setLineNo(request.getCounterAngsuran());
        subroSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
        subroSummary.setNominalSubrogasLebih(nominalSubrogasiLebih);
        subroSummary.setJenisTransaksi(request.getJenisTransaksi());
        subroSummary.setKodeBank(request.getKodeBank());
        subroSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
        subroSummary.setTglNotaKredit(new Date());
        subroSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+"_"+request.getNoRekening()+"_"+lineNomor);
        subroSummary.setSubrogasiId(subrogasiId);
        subroSummary.setNominalSubrogasiBunga(Double.valueOf(0));
        subroSummary.setNominalSubrogasiDenda(Double.valueOf(0));
        subroSummary.setNominalPajak(Double.valueOf(0));
        subroSummary.setNominalFeeGross(Double.valueOf(0));
        subroSummary.setNominalFeeNet(Double.valueOf(0));
        subroSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
        subroSummary.setTanggalJurnal(null);
        subroSummary.setNoJurnal(null);
        subroSummary.setBiayaRekonsiliasi(Double.valueOf(0));
        subroSummary.setCollectingAgent(null);
        subroSummary.setNoNotaKredit(null);
        subroSummary.setmRekeningGiro(null);
        subroSummary.setIsNetting((byte) 0);
        subroSummary.setStatus("0");

        return subrogasiSummaryRepository.save(subroSummary);
    }

    public Iterable<T_Subrogasi_Summary> getAll(){
        return subrogasiSummaryRepository.findAll();
    }

    public T_Subrogasi_Summary update(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry, String id){

        Integer lineNomor = request.getCounterAngsuran();
        Double nominalSubrogasiLebih = request.getNilaiRecoveries()-cInquiry.getAmtSubrogation();
        if(!(subrogasiSummaryRepository.findById(id).isPresent())){
            throw new RuntimeException("subrogasi dengan id:" + id+ "tidak ditemukan");
        }
        T_Subrogasi_Summary updtSubrogasiSummary = subrogasiSummaryRepository.findById(id).get();
        updtSubrogasiSummary.setLineNo(request.getCounterAngsuran());
        updtSubrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
        updtSubrogasiSummary.setNominalSubrogasLebih(nominalSubrogasiLebih);
        updtSubrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
        updtSubrogasiSummary.setKodeBank(request.getKodeBank());
        updtSubrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
        updtSubrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+"_"+request.getNoRekening()+"_"+lineNomor);
        updtSubrogasiSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
        return subrogasiSummaryRepository.save(updtSubrogasiSummary);
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

package com.test.demotest.service.aos;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public T_Subrogasi_Summary save(RequestSubrogasi request, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry,
            T_Subrogasi subrogasiId, String logicSubro) {

        Integer lineNomor = request.getCounterAngsuran();
        Double nominalSubrogasiLebih;

        if (logicSubro == "logic_subro_<=_0") {
            nominalSubrogasiLebih = 0.0;
        } else if (logicSubro == "logic_subro_>_0") {
            nominalSubrogasiLebih = request.getNilaiRecoveries() - cInquiry.getAmtSubrogation() < 0.0 ? 0.0
                    : request.getNilaiRecoveries() - cInquiry.getAmtSubrogation();
        } else {
            nominalSubrogasiLebih = 0.0;
        }

        T_Subrogasi_Summary subroSummary = new T_Subrogasi_Summary();
        subroSummary.setId(UUID.randomUUID().toString());
        subroSummary.setSubrogasiId(subrogasiId);
        subroSummary.setLineNo(request.getCounterAngsuran());
        subroSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
        subroSummary.setJenisTransaksi(request.getJenisTransaksi());
        subroSummary.setKodeBank(request.getKodeBank());
        subroSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
        subroSummary.setTglNotaKredit(new Date());
        subroSummary.setRemark("RECOV" + request.getJenisTransaksi() + "2" + "_" + cInquiry.getBpUnitCode() + "_"
                + request.getNoRekening() + "_" + lineNomor);
        subroSummary.setNominalSubrogasiBunga(Double.valueOf(0));
        subroSummary.setNominalSubrogasiDenda(Double.valueOf(0));
        subroSummary.setNominalPajak(Double.valueOf(0));
        subroSummary.setNominalFeeGross(Double.valueOf(0));
        subroSummary.setNominalFeeNet(Double.valueOf(0));
        subroSummary.setNominalSubrogasiTotal(request.getNilaiRecoveries());
        subroSummary.setNominalSubrogasLebih(nominalSubrogasiLebih);
        subroSummary.setTanggalJurnal(null);
        subroSummary.setNoJurnal(null);
        subroSummary.setBiayaRekonsiliasi(Double.valueOf(0));
        subroSummary.setCollectingAgent(null);
        subroSummary.setNoNotaKredit(null);
        subroSummary.setmRekeningGiro(null);
        subroSummary.setIsNetting((byte) 0);
        subroSummary.setStatus("0");

        T_Subrogasi_Summary createSubroSummary = subrogasiSummaryRepository.save(subroSummary);

        if (logicSubro == "logic_subro_<=_0") {

            List<T_Subrogasi_Summary> subroSummaryBySubroId = findBySubroId(subrogasiId.getId());
            List<T_Subrogasi_Summary> subroSummaryFilteredByDate = subroSummaryBySubroId.stream().sorted(Comparator.comparing(T_Subrogasi_Summary::getCreatedDate)).collect(Collectors.toList());
            T_Subrogasi_Summary getLastIndex = subroSummaryFilteredByDate.size() < 2 ? subroSummaryFilteredByDate.get(subroSummaryFilteredByDate.size() - 1):subroSummaryFilteredByDate.get(subroSummaryFilteredByDate.size() - 2);
            Double nominalLebihSubroExisting = getLastIndex.getNominalSubrogasLebih();

            nominalSubrogasiLebih = request.getNilaiRecoveries() + nominalLebihSubroExisting;
            T_Subrogasi_Summary updtNominalSubrogasiSummary = subrogasiSummaryRepository.findById(createSubroSummary.getId()).get();
            updtNominalSubrogasiSummary.setId(createSubroSummary.getId());
            updtNominalSubrogasiSummary.setNominalSubrogasLebih(nominalSubrogasiLebih);

            return subrogasiSummaryRepository.save(updtNominalSubrogasiSummary);

        }  else {
            return createSubroSummary;
        }    

    }

    public Iterable<T_Subrogasi_Summary> getAll() {
        return subrogasiSummaryRepository.findAll();
    }

    public T_Subrogasi_Summary getById(String id) {
        if (!(subrogasiSummaryRepository.findById(id).isPresent())) {
            throw new RuntimeException("subrogasi dengan id:" + id + "tidak ditemukan");
        }
        return subrogasiSummaryRepository.findById(id).get();
    }

    public void deleteById(String id) {
        subrogasiSummaryRepository.deleteById(id);
    }

    public T_Subrogasi_Summary findByLineNo(Integer counterAngsuran) {
        return subrogasiSummaryRepository.findByLineNo(counterAngsuran);
    }

    public List<T_Subrogasi_Summary> findBySubroId(String subroId) {
        return subrogasiSummaryRepository.findBySubrogasiId(subroId);
    }
}

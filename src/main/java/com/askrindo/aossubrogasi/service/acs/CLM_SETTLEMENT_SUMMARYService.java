package com.askrindo.aossubrogasi.service.acs;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askrindo.aossubrogasi.dto.RequestSubrogasi;
import com.askrindo.aossubrogasi.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.askrindo.aossubrogasi.entity.acs.CLM_SETTLEMENT;
import com.askrindo.aossubrogasi.entity.acs.CLM_SETTLEMENT_SUMMARY;
import com.askrindo.aossubrogasi.repository.acs.CLM_SETTLEMENT_SUMMARYRepository;

@Service
@Transactional
public class CLM_SETTLEMENT_SUMMARYService {

    @Autowired
    private CLM_SETTLEMENT_SUMMARYRepository clmSettlementSummaryRepo;

    public CLM_SETTLEMENT_SUMMARY create ( CLM_SETTLEMENT clmSettle, CLM_INQUIRY_SUBROGATION_CREDIT cInquiry){

        CLM_SETTLEMENT_SUMMARY createClmSummary = new CLM_SETTLEMENT_SUMMARY();
        createClmSummary.setSettleSummaryId(UUID.randomUUID().toString());
        createClmSummary.setSettlementId(clmSettle);
        createClmSummary.setCurrencyCode("IDR");
        createClmSummary.setAmtClaim(cInquiry.getAmtRecovery());
        createClmSummary.setRateIdr(Double.valueOf(0));
        
        return clmSettlementSummaryRepo.save(createClmSummary);
    }

    public List<CLM_SETTLEMENT_SUMMARY> getByCreatedBy(String createdBy){
        return clmSettlementSummaryRepo.findByCreatedBy(createdBy);
    }

    public void delete(String settlementSummaryId){
         clmSettlementSummaryRepo.deleteById(settlementSummaryId);
    }



    
}

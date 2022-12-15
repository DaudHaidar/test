package com.test.demotest.service.acs;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_SETTLEMENT;
import com.test.demotest.entity.acs.CLM_SETTLEMENT_SUMMARY;
import com.test.demotest.repository.acs.CLM_SETTLEMENT_SUMMARYRepository;

@Service
@Transactional
public class CLM_SETTLEMENT_SUMMARYService {

    @Autowired
    private CLM_SETTLEMENT_SUMMARYRepository clmSettlementSummaryRepo;

    public CLM_SETTLEMENT_SUMMARY create ( CLM_SETTLEMENT clmSettle, String currencyCode,Double amtClaim, Double rateIdr){

        CLM_SETTLEMENT_SUMMARY createClmSummary = new CLM_SETTLEMENT_SUMMARY();
        createClmSummary.setSettleSummaryId(UUID.randomUUID().toString());
        createClmSummary.setSettlementId(clmSettle);
        createClmSummary.setCurrencyCode(currencyCode);
        createClmSummary.setAmtClaim(amtClaim);
        createClmSummary.setRateIdr(rateIdr);
        
        return clmSettlementSummaryRepo.save(createClmSummary);
    }



    
}

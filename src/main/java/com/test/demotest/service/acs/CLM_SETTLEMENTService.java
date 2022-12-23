package com.test.demotest.service.acs;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_PRELIMINARY;
import com.test.demotest.entity.acs.CLM_SETTLEMENT;
import com.test.demotest.repository.acs.CLM_SETTLEMENTRepository;

@Service
@Transactional
public class CLM_SETTLEMENTService{
    @Autowired
    private CLM_SETTLEMENTRepository clmSettlementRepository;

    // public CLM_SETTLEMENT create(String registrationId,String clmSettleType,String PreliminaryId, String clmCompletionType, String paymentOption, String billedTo , String SettlementNo,Integer docStatus, Integer docTypeId,Integer settlementIdx, Character nonPropTreaty, Integer printCounter){
        
    //     CLM_SETTLEMENT createSettlement = new CLM_SETTLEMENT();
    //     createSettlement.setSettlementId(UUID.randomUUID().toString());
    //     createSettlement.setRegistrationId(registrationId);
    //     createSettlement.setDocStatus(docStatus);
    //     createSettlement.setDocTypeId(docTypeId);
    //     createSettlement.setPremliminaryId(PreliminaryId);
    //     createSettlement.setSettlementIdx(settlementIdx);
    //     createSettlement.setClmSettleType(clmSettleType);;
    //     createSettlement.setClmCompletionType(clmCompletionType);
    //     createSettlement.setPaymentOption(paymentOption);
    //     createSettlement.setBilledTo(billedTo);
    //     createSettlement.setIssuDate(new Date());
    //     createSettlement.setSettlementNo(SettlementNo);
    //     createSettlement.setIsNonpropTreaty(nonPropTreaty);
    //     createSettlement.setPrintCounter(printCounter);

    //     return clmSettlementRepository.save(createSettlement);
    // }

    public CLM_SETTLEMENT create(CLM_INQUIRY_SUBROGATION_CREDIT cInquiry,CLM_PRELIMINARY cPreliminary){
        
        CLM_SETTLEMENT createSettlement = new CLM_SETTLEMENT();
        createSettlement.setSettlementId(UUID.randomUUID().toString());
        createSettlement.setRegistrationId(cInquiry.getRegistrationId());
        createSettlement.setDocStatus(5 );
        createSettlement.setDocTypeId(12);
        createSettlement.setPremliminaryId(cPreliminary.getPreliminaryId());
        createSettlement.setSettlementIdx(1);
        createSettlement.setClmSettleType("CLM_SETTLE_TYPE.SUBROGATION_13");;
        createSettlement.setClmCompletionType("CLM_COMPLETION_TYPE.TEKNIS");
        createSettlement.setPaymentOption("CLM_OPSI_PEMBAYARAN.PENUH");
        createSettlement.setBilledTo(null);
        createSettlement.setIssuDate(new Date());
        createSettlement.setSettlementNo(null);
        createSettlement.setIsNonpropTreaty('0');
        createSettlement.setPrintCounter(0);

        return clmSettlementRepository.save(createSettlement);
    }

    public void delete(String settlementId){
        clmSettlementRepository.deleteById(settlementId);
    }

}

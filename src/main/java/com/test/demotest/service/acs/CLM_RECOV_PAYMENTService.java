package com.test.demotest.service.acs;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.repository.acs.CLM_RECOV_PAYMENTRepository;

@Service
public class CLM_RECOV_PAYMENTService {
    @Autowired
    private CLM_RECOV_PAYMENTRepository recovPaymentRepository;

    public  CLM_RECOV_PAYMENT update (CLM_RECOV_PAYMENT request, String id, Character isaidOffFee,String registrationId, String analysisId,String settlementId,Integer reverseMultiplier,Character isNetting, Double amtShsPrev, Double amtShsAfter,String branchID,Character isPaidOffReconciliation, String recoveryType){

        CLM_RECOV_PAYMENT createRecovPayment = new CLM_RECOV_PAYMENT();
        createRecovPayment.setPaymentId(UUID.randomUUID().toString());
        createRecovPayment.setIsPaidOffFee(isaidOffFee);
        createRecovPayment.setRegistrationId(registrationId);
        createRecovPayment.setAnalysisId(analysisId);
        createRecovPayment.setSettlementId(settlementId);
        createRecovPayment.setReverseMultiplier(reverseMultiplier);
        createRecovPayment.setIsNetting(isNetting);
        createRecovPayment.setAmtShsPrev(amtShsPrev);
        createRecovPayment.setAmtShsAfter(amtShsAfter);
        createRecovPayment.setBranchId(id);
        createRecovPayment.setIsPaidOffReconciliation(isPaidOffReconciliation);
        createRecovPayment.setRecoveryType(recoveryType);
        return recovPaymentRepository.save(createRecovPayment);
    }
        
}

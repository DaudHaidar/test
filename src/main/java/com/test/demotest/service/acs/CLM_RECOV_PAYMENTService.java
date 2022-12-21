package com.test.demotest.service.acs;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.repository.acs.CLM_RECOV_PAYMENTRepository;

@Service
@Transactional
public class CLM_RECOV_PAYMENTService {
    @Autowired
    private CLM_RECOV_PAYMENTRepository recovPaymentRepository;

    public  CLM_RECOV_PAYMENT create ( String branchId, Character isPaidOffFee,String registrationId, String analysisId,String settlementId,Integer reverseMultiplier,Character isNetting, Double amtShsPrev, Double amtShsAfter,Character isPaidOffReconciliation, String recoveryType){

        CLM_RECOV_PAYMENT createRecovPayment = new CLM_RECOV_PAYMENT();
        createRecovPayment.setPaymentId(UUID.randomUUID().toString());
        createRecovPayment.setIsPaidOffFee(isPaidOffFee);
        createRecovPayment.setRegistrationId(registrationId);
        createRecovPayment.setAnalysisId(analysisId);
        createRecovPayment.setSettlementId(settlementId);
        createRecovPayment.setReverseMultiplier(reverseMultiplier);
        createRecovPayment.setIsNetting(isNetting);
        createRecovPayment.setAmtShsPrev(amtShsPrev);
        createRecovPayment.setAmtShsAfter(amtShsAfter<0.0? 0.0:amtShsAfter);
        createRecovPayment.setBranchId(branchId);
        createRecovPayment.setIsPaidOffReconciliation(isPaidOffReconciliation);
        createRecovPayment.setRecoveryType(recoveryType);
        return recovPaymentRepository.save(createRecovPayment);
    }

    public void delete(String id){
        recovPaymentRepository.deleteById(id);
    }

    public List<CLM_RECOV_PAYMENT> findByCreatedBy(String createdBy){
        return recovPaymentRepository.findByCreatedBy(createdBy);
    }
        
}

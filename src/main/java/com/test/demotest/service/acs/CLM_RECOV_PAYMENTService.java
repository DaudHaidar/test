package com.test.demotest.service.acs;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.entity.acs.CLM_SETTLEMENT;
import com.test.demotest.repository.acs.CLM_RECOV_PAYMENTRepository;

@Service
@Transactional
public class CLM_RECOV_PAYMENTService {
    @Autowired
    private CLM_RECOV_PAYMENTRepository recovPaymentRepository;

    public  CLM_RECOV_PAYMENT create ( CLM_INQUIRY_SUBROGATION_CREDIT cInquiry,CLM_SETTLEMENT cSettlement, RequestSubrogasi request ){

        Double amtShsAfter = cInquiry.getAmtSubrogation()- request.getNilaiRecoveries();

        CLM_RECOV_PAYMENT createRecovPayment = new CLM_RECOV_PAYMENT();
        createRecovPayment.setPaymentId(UUID.randomUUID().toString());
        createRecovPayment.setIsPaidOffFee('0');
        createRecovPayment.setRegistrationId(cInquiry.getRegistrationId());
        createRecovPayment.setAnalysisId(cInquiry.getAnalysisId());
        createRecovPayment.setSettlementId(cSettlement.getSettlementId());
        createRecovPayment.setReverseMultiplier(1);
        createRecovPayment.setIsNetting('0');
        createRecovPayment.setAmtShsPrev(cInquiry.getAmtSubrogation());
        createRecovPayment.setAmtShsAfter(amtShsAfter<0.0? 0.0:amtShsAfter);
        createRecovPayment.setBranchId(cInquiry.getBranchId());
        createRecovPayment.setIsPaidOffReconciliation('0');
        createRecovPayment.setRecoveryType("RECOVERY_TYPE_KUR.REC");
        return recovPaymentRepository.save(createRecovPayment);
    }

    public void delete(String id){
        recovPaymentRepository.deleteById(id);
    }

    public List<CLM_RECOV_PAYMENT> findByCreatedBy(String createdBy){
        return recovPaymentRepository.findByCreatedBy(createdBy);
    }
        
}

package com.test.demotest.service.acs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    public CLM_SETTLEMENT create(CLM_INQUIRY_SUBROGATION_CREDIT cInquiry,CLM_PRELIMINARY cPreliminary){

        List<CLM_SETTLEMENT> settlement = new ArrayList<CLM_SETTLEMENT>(clmSettlementRepository.findByRegistrationId(cInquiry.getRegistrationId()));
        Integer version =0;

        List<CLM_SETTLEMENT> settlementSortedByDate = settlement.stream().sorted(Comparator.comparing(CLM_SETTLEMENT::getCreatedDate)).collect(Collectors.toList());
        System.out.println("CLM SORTED BY DATE: "+ settlementSortedByDate);
        
        CLM_SETTLEMENT lastIndexRegistration = settlementSortedByDate.get(settlement.size()-1);

        System.out.println("LAST INDEX REGSITRATION : "+lastIndexRegistration.toString());
        System.out.println("VERSION  lastIndexRegistration : "+lastIndexRegistration.getVersion());

        if(lastIndexRegistration.getVersion()==null){
            version = 1;
            System.out.println("VERSION last :"+ "null");
        }else{
            version = lastIndexRegistration.getVersion()+1;
            System.out.println("VERSION PLUS 1 :"+ version);
        }



        System.out.println("VERSION after plus :"+ version);
        System.out.println("clm size :"+settlementSortedByDate.size());

        

        CLM_SETTLEMENT createSettlement = new CLM_SETTLEMENT();
        createSettlement.setVersion(version);
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

/**
 * com.test.demotest.entity.acs.CLM_SETTLEMENT@4793c14b]
LAST INDEX REGSITRATION : com.test.demotest.entity.acs.CLM_SETTLEMENT@4793c14b
VERSION  lastIndexRegistration : 0
VERSION PLUS 1 :1
 */

 /**
  * LAST INDEX REGSITRATION : com.test.demotest.entity.acs.CLM_SETTLEMENT@322f55ad
VERSION  lastIndexRegistration : 0
VERSION last :null
VERSION after plus :1
  */

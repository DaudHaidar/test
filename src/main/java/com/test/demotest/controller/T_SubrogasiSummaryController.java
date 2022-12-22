package com.test.demotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.dto.ResponseData;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_PRELIMINARY;
import com.test.demotest.entity.acs.CLM_RECOV_PAYMENT;
import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;
import com.test.demotest.entity.acs.CLM_SETTLEMENT;
import com.test.demotest.entity.acs.CLM_SETTLEMENT_SUMMARY;
import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.entity.aos.T_Subrogasi_Summary;
import com.test.demotest.service.acs.CLM_INQUIRY_SUBROGRATIONService;
import com.test.demotest.service.acs.CLM_PRELIMINARYService;
import com.test.demotest.service.acs.CLM_RECOV_PAYMENTService;
import com.test.demotest.service.acs.CLM_REGISTRATION_OSService;
import com.test.demotest.service.acs.CLM_SETTLEMENTService;
import com.test.demotest.service.acs.CLM_SETTLEMENT_SUMMARYService;
import com.test.demotest.service.aos.LogsService;
import com.test.demotest.service.aos.T_SubrogasiService;
import com.test.demotest.service.aos.T_SubrogasiSummaryService;

@RestController
public class T_SubrogasiSummaryController {
    
    @Autowired
    private T_SubrogasiSummaryService subrogasiSummaryService;
    @Autowired
    private T_SubrogasiService subrogasiService;
    @Autowired
    private CLM_INQUIRY_SUBROGRATIONService cInquiryService;
    @Autowired
    private CLM_PRELIMINARYService cPreliminaryService;
    @Autowired
    private CLM_SETTLEMENTService cSettlementService;
    @Autowired
    private CLM_SETTLEMENT_SUMMARYService cSettlementSummaryService;
    @Autowired
    private CLM_RECOV_PAYMENTService cRecovePaymentService;
    @Autowired
    private CLM_REGISTRATION_OSService cRegistrationOsService;
    @Autowired
    private LogsService logsService;

    @PostMapping("/api/v1/shs/subro")
    public ResponseEntity<ResponseData<Object>> create(@RequestBody RequestSubrogasi request){

    try {
        CLM_INQUIRY_SUBROGATION_CREDIT cInquiry = cInquiryService.getByNoRekening(request.getNoRekening());
        CLM_PRELIMINARY cPreliminary = cPreliminaryService.findRegistrationId(cInquiry.getRegistrationId());
        

        System.out.println("CEK SISA SUBROGARATION "+cInquiry.getAmtSubrogation());
        if(cInquiry != null){

            T_Subrogasi_Summary lineNo = subrogasiSummaryService.findByLineNo(request.getCounterAngsuran());

            if(lineNo != null){
                System.out.println("Subrogation2 line no :"+lineNo.getLineNo());
                throw new Exception("Counter angsuran sudah terdaftar");
            } 
    
            if(lineNo == null&& request.getCounterAngsuran()==1){
                if(cInquiry.getAmtSubrogation() <= 0){     

                    T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry);

                    T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_<_0");

                    ResponseData<Object> response = new ResponseData<Object>();
                    response.setStatus("00");
                    response.setMessage("00");
                    response.getData().add(subrogasiSummary);

                    logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                    return ResponseEntity.status(HttpStatus.CREATED).body(response);

                }
                else if(cInquiry.getAmtSubrogation() >0){
                    
                    T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry);

                    T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_>_0");                   

                    CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry.getRegistrationId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0);

                    CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement,"IDR", Double.valueOf(1000000), Double.valueOf(1));

                    CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create(cInquiry.getBranchId(), '0', cInquiry.getRegistrationId(), cInquiry.getAnalysisId(), cSettlement.getSettlementId(), 1, '0', cInquiry.getAmtSubrogation(), cInquiry.getAmtSubrogation()-request.getNilaiRecoveries(), '0', "RECOVERY_TYPE_KUR.REC");

                    
                    ResponseData<Object> response = new ResponseData<Object>();
                    response.setStatus("00");
                    response.setMessage("00");
                    response.getData().add(subrogasiSummary);
                    response.getData().add(cSettlementSummary);
                    response.getData().add(cRecovPayment);

                    CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry.getRegistrationId(), cRecovPayment.getAmtShsAfter(), cInquiry.getAmtClaimPayment()-cRecovPayment.getAmtShsAfter());
                    response.getData().add(cRegistrationOs);

                    logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                }            
            }  
            
                if(request.getCounterAngsuran()!=1 && subrogasiSummaryService.findByLineNo(request.getCounterAngsuran()-1) != null){

                    if(cInquiry.getAmtSubrogation() <= 0){

                        T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry);

                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_<_0");

                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(subrogasiSummary);

                        logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                        return ResponseEntity.status(HttpStatus.CREATED).body(response);

                    } 
                    
                    else if(cInquiry.getAmtSubrogation() > 0){

                        T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry);

                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_>_0");                   

                        CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry.getRegistrationId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0);

                        CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement,"IDR", Double.valueOf(1000000), Double.valueOf(1));

                        CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create(cInquiry.getBranchId(), '0', cInquiry.getRegistrationId(), cInquiry.getAnalysisId(), cSettlement.getSettlementId(), 1, '0', cInquiry.getAmtSubrogation(), cInquiry.getAmtSubrogation()-request.getNilaiRecoveries(), '0', "RECOVERY_TYPE_KUR.REC");

                        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(subrogasiSummary);
                        response.getData().add(cSettlementSummary);
                        response.getData().add(cRecovPayment);

                        CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry.getRegistrationId(), cRecovPayment.getAmtShsAfter(), cInquiry.getAmtClaimPayment()-cRecovPayment.getAmtShsAfter());
                        response.getData().add(cRegistrationOs);

                        logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());

                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    }
            }
        }
        
        throw new Exception("Data cInquiry tidak ditemukan atau value counter angsuran longkap");
        
    } catch (Exception e) {

            ResponseData<Object> response = new ResponseData<Object>();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            System.out.println(e);
            logsService.create(request.getNoRekening(), "h2h-subro", response.toString(), response.getStatus(), request.toString(), response.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  
        }        
    }

    @GetMapping("/api/v1/shs/subro/noRek/{noRekening}")
    public T_Subrogasi get(@PathVariable("noRekening") String noRekening){
        return subrogasiService.findByNoRekening(noRekening);
    }
}

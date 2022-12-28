package com.test.demotest.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        if(cInquiry != null){

            CLM_PRELIMINARY cPreliminary = cPreliminaryService.findRegistrationId(cInquiry.getRegistrationId());
            T_Subrogasi subroByNoRek = subrogasiService.findByNoRekening(request.getNoRekening());

            if(subroByNoRek == null){  

                Integer counterAngsuran = request.getCounterAngsuran();

                if(counterAngsuran ==1){
                    if(cInquiry.getAmtSubrogation() <= 0){

                        T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry,null);
                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_<=_0");
        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(subrogasiSummary);
        
                        logsService.create(request, response);
        
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);

                    } else if(cInquiry.getAmtSubrogation() > 0){

                        CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry,cPreliminary);

                        CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement, cInquiry);
    
                        CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create( cInquiry, cSettlement,request);
                        T_Subrogasi subrogasi = subrogasiService.save(request,cInquiry,cRecovPayment);
                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_>_0");
        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        
                        response.getData().add(cSettlementSummary);
                        response.getData().add(cRecovPayment);

                        CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry,cRecovPayment);

                        response.getData().add(cRegistrationOs);
                        response.getData().add(subrogasiSummary);

                        logsService.create(request, response);
        
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    }
                }
                else if(counterAngsuran != 1){
                    throw new RuntimeException("Error ! counter angsuran harus dimulai dari 1");
                }    
            }
            else if(subroByNoRek != null){

                List<T_Subrogasi_Summary> subroSummaryBySubroId= subrogasiSummaryService.findBySubroId(subroByNoRek.getId());
                List<T_Subrogasi_Summary> subroSummaryBySubroIdSortedByDate = subroSummaryBySubroId.stream().sorted(Comparator.comparing(T_Subrogasi_Summary::getCreatedDate)).collect(Collectors.toList());
                
                Integer getLastLineNo = subroSummaryBySubroIdSortedByDate.get(subroSummaryBySubroIdSortedByDate.size()-1).getLineNo();

                Integer counterAngsuranExist = request.getCounterAngsuran();

                //cek line_no sudah ada atau belum di database table subrogasi_summary
                boolean lineNoExists = subroSummaryBySubroId.stream().map(T_Subrogasi_Summary::getLineNo).anyMatch(counterAngsuranExist::equals);

                Integer counterAngsuranSequential = request.getCounterAngsuran()-1;
                
                //cek line_no loncat atau tidak di database table subrogasi_summary
                boolean lineNoSequential = subroSummaryBySubroId.stream().map(T_Subrogasi_Summary::getLineNo).anyMatch(counterAngsuranSequential::equals);

                if(lineNoExists == true){
                    throw new Exception("Counter angsuran sudah terdaftar");
                }if(lineNoExists == false&& request.getCounterAngsuran()==1){
                    if(cInquiry.getAmtSubrogation() <= 0){
                        T_Subrogasi subrogasi = subrogasiService.update(request,cInquiry,subroByNoRek.getId(),null);
                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_<=_0");
        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(subrogasiSummary);
        
                        logsService.create(request, response);
        
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);

                    } else if(cInquiry.getAmtSubrogation() > 0){
                        CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry,cPreliminary);

                        CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement, cInquiry);
    
                        CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create( cInquiry, cSettlement,request);
    
                        T_Subrogasi subrogasi = subrogasiService.update(request,cInquiry,subroByNoRek.getId(),cRecovPayment);

                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_>_0");
        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        
                        response.getData().add(cSettlementSummary);
                        response.getData().add(cRecovPayment);

                        CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry,cRecovPayment);

                        response.getData().add(cRegistrationOs);
                        response.getData().add(subrogasiSummary);

                        logsService.create(request, response);
        
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    }

                }if(request.getCounterAngsuran()!=1 && lineNoSequential == true){

                    if(cInquiry.getAmtSubrogation() <= 0){

                        T_Subrogasi subrogasi = subrogasiService.update(request,cInquiry,subroByNoRek.getId(),null);
                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_<=_0");
        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(subrogasiSummary);
        
                        logsService.create(request, response);
        
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);

                    } else if(cInquiry.getAmtSubrogation() > 0){
                        CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry,cPreliminary);

                        CLM_SETTLEMENT_SUMMARY cSettlementSummary = cSettlementSummaryService.create( cSettlement, cInquiry);
    
                        CLM_RECOV_PAYMENT cRecovPayment = cRecovePaymentService.create( cInquiry, cSettlement,request);
                            
                        T_Subrogasi subrogasi = subrogasiService.update(request,cInquiry,subroByNoRek.getId(),cRecovPayment);

                        T_Subrogasi_Summary subrogasiSummary = subrogasiSummaryService.save(request,cInquiry,subrogasi,"logic_subro_>_0");
        
                        ResponseData<Object> response = new ResponseData<Object>();
                        response.setStatus("00");
                        response.setMessage("00");
                        
                        response.getData().add(cSettlementSummary);
                        response.getData().add(cRecovPayment);

                        CLM_REGISTRATION_OS cRegistrationOs =cRegistrationOsService.update(cInquiry,cRecovPayment);

                        response.getData().add(cRegistrationOs);
                        response.getData().add(subrogasiSummary);

                        logsService.create(request, response);
        
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    }

                }else if(lineNoSequential == false){
                    throw new Exception("error! counter angsuran harus berururutan tidak diperbolehkan loncat. Counter angsuran terakhir : " + getLastLineNo);
                }     
            }
        }
        throw new Exception("Data cInquiry tidak ditemukan");
        
    } catch (Exception e) {

            ResponseData<Object> response = new ResponseData<Object>();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            
            logsService.create(request, response);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }  
    }
        
}

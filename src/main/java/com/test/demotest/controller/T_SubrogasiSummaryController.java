package com.test.demotest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.dto.ResponseData;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.entity.acs.CLM_PRELIMINARY;
import com.test.demotest.entity.aos.T_Subrogasi;
import com.test.demotest.entity.aos.T_Subrogasi_Summary;
import com.test.demotest.service.acs.CLM_INQUIRY_SUBROGRATIONService;
import com.test.demotest.service.acs.CLM_PRELIMINARYService;
import com.test.demotest.service.acs.CLM_SETTLEMENTService;
import com.test.demotest.service.acs.CLM_SETTLEMENT_SUMMARYService;
import com.test.demotest.service.aos.T_SubrogasiService;
import com.test.demotest.service.aos.T_SubrogasiSummaryService;

@RestController
public class T_SubrogasiSummaryController {
    
    @Autowired
    private T_SubrogasiSummaryService subrogasiSummaryService;
    @Autowired
    private T_SubrogasiService subrogasiService;
    @Autowired
    private CLM_INQUIRY_SUBROGRATIONService acsService;
    @Autowired
    private CLM_PRELIMINARYService cPreliminaryService;
    @Autowired
    private CLM_SETTLEMENTService cSettlementService;
    @Autowired
    private CLM_SETTLEMENT_SUMMARYService cSettlementSummaryService;

    @PostMapping("/api/v1/shs/subro")
    public ResponseEntity<ResponseData<T_Subrogasi_Summary>> create(@RequestBody RequestSubrogasi request){

        try {
            CLM_INQUIRY_SUBROGATION_CREDIT acs = acsService.getByNoRekening(request.getNoRekening());
            CLM_PRELIMINARY cPreliminary = cPreliminaryService.findRegistrationId(request.getRegistrationId());


            if(acs != null){

                T_Subrogasi_Summary subrogasiSummary = new T_Subrogasi_Summary();
                T_Subrogasi subrogasi= new T_Subrogasi();
                T_Subrogasi_Summary lineNo = subrogasiSummaryService.findByLineNo(request.getCounterAngsuran());

                if(lineNo != null){
                    System.out.println("Subrogation2 line no :"+lineNo.getLineNo());
                    throw new Exception("Counter angsuran sudah terdaftar");
                } 

                 if(lineNo == null&& request.getCounterAngsuran()==1){
                        if(acs.getAmtSubrogation() != 0){

                            subrogasi.setNoRekening(request.getNoRekening());
                            subrogasi.setNomorPeserta(request.getNoRekening());
                            subrogasi.setNominalClaim(request.getNilaiRecoveries());
                            subrogasi.setAkumulasiSubrogasi(acs.getAmtRecovery()+request.getNilaiRecoveries());
                            subrogasi.setSisaKewajibanSubrogasi(acs.getAmtSubrogation());
                            subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                            subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                            subrogasi.setPresentasePajak(Double.valueOf(0));
                            subrogasi.setIdKlaim(UUID.randomUUID().toString());
                            subrogasi.setStatus("0");

                            subrogasiSummary.setLineNo(request.getCounterAngsuran());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-acs.getAmtSubrogation());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                            subrogasiSummary.setKodeBank(request.getKodeBank());
                            subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                            subrogasiSummary.setTglNotaKredit(new Date());
                            subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+acs.getBpUnitCode()+request.getNoRekening()+request.getCounterAngsuran());
                            subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
                            subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
                            subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
                            subrogasiSummary.setNominalPajak(Double.valueOf(0));
                            subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
                            subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
                            subrogasiSummary.setTanggalJurnal(null);
                            subrogasiSummary.setNoJurnal(null);
                            subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
                            subrogasiSummary.setCollectingAgent(null);
                            subrogasiSummary.setNoNotaKredit(null);
                            subrogasiSummary.setmRekeningGiro(null);
                            subrogasiSummary.setIsNetting((byte) 0);
                            subrogasiSummary.setStatus("0");

                            ResponseData<T_Subrogasi_Summary> response = new ResponseData<T_Subrogasi_Summary>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

                            return ResponseEntity.status(HttpStatus.CREATED).body(response);
                        }
                        // else untuk update ke data table entity lain  
                        cSettlementSummaryService.create(cSettlementService.create(acs.getId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0), "IDR", Double.valueOf(1000000), Double.valueOf(1));
                                           
                 }  
                    if(request.getCounterAngsuran()!=1 && subrogasiSummaryService.findByLineNo(request.getCounterAngsuran()-1) != null){

                        if(acs.getAmtSubrogation() != 0){
                            subrogasi.setNoRekening(request.getNoRekening());
                            subrogasi.setNomorPeserta(request.getNoRekening());
                            subrogasi.setNominalClaim(request.getNilaiRecoveries());
                            subrogasi.setAkumulasiSubrogasi(acs.getAmtRecovery()+request.getNilaiRecoveries());
                            subrogasi.setSisaKewajibanSubrogasi(acs.getAmtSubrogation());
                            subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                            subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                            subrogasi.setPresentasePajak(Double.valueOf(0));
                            subrogasi.setIdKlaim(UUID.randomUUID().toString());
                            subrogasi.setStatus("0");

                            subrogasiSummary.setLineNo(request.getCounterAngsuran());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-acs.getAmtSubrogation());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                            subrogasiSummary.setKodeBank(request.getKodeBank());
                            subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                            subrogasiSummary.setTglNotaKredit(new Date());
                            subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+acs.getBpUnitCode()+request.getNoRekening()+request.getCounterAngsuran());
                            subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
                            subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
                            subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
                            subrogasiSummary.setNominalPajak(Double.valueOf(0));
                            subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
                            subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
                            subrogasiSummary.setTanggalJurnal(null);
                            subrogasiSummary.setNoJurnal(null);
                            subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
                            subrogasiSummary.setCollectingAgent(null);
                            subrogasiSummary.setNoNotaKredit(null);
                            subrogasiSummary.setmRekeningGiro(null);
                            subrogasiSummary.setIsNetting((byte) 0);
                            subrogasiSummary.setStatus("0");

                            ResponseData<T_Subrogasi_Summary> response = new ResponseData<T_Subrogasi_Summary>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

                            return ResponseEntity.status(HttpStatus.CREATED).body(response);

                        }                        
                        // else untuk update ke data table entity lain
                        

                        ResponseData<T_Subrogasi_Summary> response = new ResponseData<T_Subrogasi_Summary>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(cSettlementSummaryService.create(cSettlementService.create(acs.getId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0), "IDR", Double.valueOf(1000000), Double.valueOf(1)););

                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                }

 
                }
            
            throw new Exception("Data acs tidak ditemukan");
        } catch (Exception e) {

            ResponseData<T_Subrogasi_Summary> response = new ResponseData<T_Subrogasi_Summary>();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            System.out.println(
                e.getMessage()            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  
        }        
    }


    //ngetest get inquiry subrogration
    @GetMapping("/api/v1/shs/subro")
    public ResponseEntity<ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT>> getInfo(@RequestBody RequestSubrogasi request){

        CLM_INQUIRY_SUBROGATION_CREDIT acs =  acsService.getByNoRekening(request.getNoRekening());
            System.out.println(acs);

            if(acs != null){
                        ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT> response = new ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(acs);

                        return ResponseEntity.status(HttpStatus.OK).body(response);  

                    }

                    ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT> response = new ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT>();
                    response.setStatus("00");
                    response.setMessage("00");
                    response.getData().add(acs);

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
              
    }

        //ngetest get lunas subrogartion 
        @GetMapping("/api/v1/shs/subro/zero")
        public ResponseEntity<ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>> getLunas(Double sisa){
            sisa= 0.0;
            List<CLM_INQUIRY_SUBROGATION_CREDIT> acs =  acsService.getByZeroSubrogration(sisa);
    
                if(acs != null){
                            ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>> response = new ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(acs);
    
                            return ResponseEntity.status(HttpStatus.OK).body(response);  
    
                        }
    
                        ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>> response = new ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(acs);
    
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
                  
        }
}


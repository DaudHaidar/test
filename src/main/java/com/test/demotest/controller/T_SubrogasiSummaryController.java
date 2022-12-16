package com.test.demotest.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

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
import com.test.demotest.entity.acs.CLM_SETTLEMENT;
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
    private CLM_INQUIRY_SUBROGRATIONService cInquiryService;
    @Autowired
    private CLM_PRELIMINARYService cPreliminaryService;
    @Autowired
    private CLM_SETTLEMENTService cSettlementService;
    @Autowired
    private CLM_SETTLEMENT_SUMMARYService cSettlementSummaryService;

    @PostMapping("/api/v1/shs/subro")
    public ResponseEntity<ResponseData<Object>> create(@RequestBody RequestSubrogasi request){

        try {
            CLM_INQUIRY_SUBROGATION_CREDIT cInquiry = cInquiryService.getByNoRekening(request.getNoRekening());
            CLM_PRELIMINARY cPreliminary = cPreliminaryService.findRegistrationId(cInquiry.getRegistrationId());


            if(cInquiry != null){

                T_Subrogasi_Summary subrogasiSummary = new T_Subrogasi_Summary();
                T_Subrogasi subrogasi= new T_Subrogasi();
                T_Subrogasi_Summary lineNo = subrogasiSummaryService.findByLineNo(request.getCounterAngsuran());

                if(lineNo != null){
                    System.out.println("Subrogation2 line no :"+lineNo.getLineNo());
                    throw new Exception("Counter angsuran sudah terdaftar");
                } 
                if(lineNo == null&& request.getCounterAngsuran()==1){
                        if(cInquiry.getAmtSubrogation() != 0){

                            subrogasi.setNoRekening(request.getNoRekening());
                            subrogasi.setNomorPeserta(request.getNoRekening());
                            subrogasi.setNominalClaim(request.getNilaiRecoveries());
                            subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                            subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                            subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                            subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                            subrogasi.setPresentasePajak(Double.valueOf(0));
                            subrogasi.setIdKlaim(UUID.randomUUID().toString());
                            subrogasi.setStatus("0");

                            subrogasiSummary.setLineNo(request.getCounterAngsuran());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                            subrogasiSummary.setKodeBank(request.getKodeBank());
                            subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                            subrogasiSummary.setTglNotaKredit(new Date());
                            subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+request.getNoRekening()+request.getCounterAngsuran());
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

                            ResponseData<Object> response = new ResponseData<Object>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

                            return ResponseEntity.status(HttpStatus.CREATED).body(response);
                        }

                        System.out.println("CEK REGISTRATION ID CLM_PRELIMINARY (cPreliminary) :"+cPreliminary);
                        System.out.println("cInquiry REGISTRATION ID (cInquiry.getId()): "+ cInquiry.getRegistrationId());
                        System.out.println("PRELIMINARY_ID (cPreliminary.getPreliminaryId()) : "+cPreliminary.getPreliminaryId());

                            subrogasi.setNoRekening(request.getNoRekening());
                            subrogasi.setNomorPeserta(request.getNoRekening());
                            subrogasi.setNominalClaim(request.getNilaiRecoveries());
                            subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                            subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                            subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                            subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                            subrogasi.setPresentasePajak(Double.valueOf(0));
                            subrogasi.setIdKlaim(UUID.randomUUID().toString());
                            subrogasi.setStatus("0");

                            subrogasiSummary.setLineNo(request.getCounterAngsuran());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                            subrogasiSummary.setKodeBank(request.getKodeBank());
                            subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                            subrogasiSummary.setTglNotaKredit(new Date());
                            subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+request.getNoRekening()+request.getCounterAngsuran());
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

                            CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry.getRegistrationId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0);

                            System.out.println("Create Settlement"+cSettlement);

                            ResponseData<Object> response = new ResponseData<Object>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(cSettlementSummaryService.create( cSettlement,"IDR", Double.valueOf(1000000), Double.valueOf(1)));
                            response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

                            return ResponseEntity.status(HttpStatus.CREATED).body(response);

                        // else untuk update ke data table entity lain            
                 }  
                if(request.getCounterAngsuran()!=1 && subrogasiSummaryService.findByLineNo(request.getCounterAngsuran()-1) != null){

                        if(cInquiry.getAmtSubrogation() != 0){

                            subrogasi.setNoRekening(request.getNoRekening());
                            subrogasi.setNomorPeserta(request.getNoRekening());
                            subrogasi.setNominalClaim(request.getNilaiRecoveries());
                            subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                            subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                            subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                            subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                            subrogasi.setPresentasePajak(Double.valueOf(0));
                            subrogasi.setIdKlaim(UUID.randomUUID().toString());
                            subrogasi.setStatus("0");

                            subrogasiSummary.setLineNo(request.getCounterAngsuran());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                            subrogasiSummary.setKodeBank(request.getKodeBank());
                            subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                            subrogasiSummary.setTglNotaKredit(new Date());
                            subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+request.getNoRekening()+request.getCounterAngsuran());
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

                            ResponseData<Object> response = new ResponseData<Object>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

                            return ResponseEntity.status(HttpStatus.CREATED).body(response);

                        }      
                        
                        System.out.println("CEK REGISTRATION ID CLM_PRELIMINARY (cPreliminary) :"+cPreliminary);
                        System.out.println("cInquiry REGISTRATION ID (cInquiry.getId()): "+ cInquiry.getRegistrationId());
                        System.out.println("PRELIMINARY_ID (cPreliminary.getPreliminaryId()) : "+cPreliminary.getPreliminaryId());

                            subrogasi.setNoRekening(request.getNoRekening());
                            subrogasi.setNomorPeserta(request.getNoRekening());
                            subrogasi.setNominalClaim(request.getNilaiRecoveries());
                            subrogasi.setAkumulasiSubrogasi(cInquiry.getAmtRecovery()+request.getNilaiRecoveries());
                            subrogasi.setSisaKewajibanSubrogasi(cInquiry.getAmtSubrogation());
                            subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
                            subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
                            subrogasi.setPresentasePajak(Double.valueOf(0));
                            subrogasi.setIdKlaim(UUID.randomUUID().toString());
                            subrogasi.setStatus("0");

                            subrogasiSummary.setLineNo(request.getCounterAngsuran());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-cInquiry.getAmtSubrogation());
                            subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
                            subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
                            subrogasiSummary.setKodeBank(request.getKodeBank());
                            subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
                            subrogasiSummary.setTglNotaKredit(new Date());
                            subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+cInquiry.getBpUnitCode()+request.getNoRekening()+request.getCounterAngsuran());
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
                           

                            CLM_SETTLEMENT cSettlement = cSettlementService.create(cInquiry.getRegistrationId(), "CLM_SETTLE_TYPE.SUBROGATION_13", cPreliminary.getPreliminaryId(), "CLM_COMPLETION_TYPE.TEKNIS", "CLM_OPSI_PEMBAYARAN.PENUH", null, null, 5, 12, 1, '0', 0);

                            System.out.println("Create Settlement"+cSettlement);

                            ResponseData<Object> response = new ResponseData<Object>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(cSettlementSummaryService.create( cSettlement,"IDR", Double.valueOf(1000000), Double.valueOf(1)));
                            response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

                            return ResponseEntity.status(HttpStatus.CREATED).body(response);
                }
            }
            
            throw new Exception("Data cInquiry tidak ditemukan");
            
        } catch (Exception e) {

            ResponseData<Object> response = new ResponseData<Object>();
            response.setStatus("01");
            response.setMessage(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  
        }        
    }


    //ngetest get inquiry subrogration by no rekeningnya
    @GetMapping("/api/v1/shs/subro")
    public ResponseEntity<ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT>> getInfo(@RequestBody RequestSubrogasi request){

        CLM_INQUIRY_SUBROGATION_CREDIT cInquiry =  cInquiryService.getByNoRekening(request.getNoRekening());
            System.out.println(cInquiry);

            if(cInquiry != null){
                        ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT> response = new ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT>();
                        response.setStatus("00");
                        response.setMessage("00");
                        response.getData().add(cInquiry);

                        return ResponseEntity.status(HttpStatus.OK).body(response);  

                    }

            ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT> response = new ResponseData<CLM_INQUIRY_SUBROGATION_CREDIT>();
            response.setStatus("00");
            response.setMessage("00");
            response.getData().add(cInquiry);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
              
    }

        //ngetest get yang sudah lunas subrogationnya

        @GetMapping("/api/v1/shs/subro/zero")
        public ResponseEntity<ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>> getLunas(Double sisa){
            sisa= 0.0;
            List<CLM_INQUIRY_SUBROGATION_CREDIT> cInquiry =  cInquiryService.getByZeroSubrogration(sisa);
    
                if(cInquiry != null){
                            ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>> response = new ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>();
                            response.setStatus("00");
                            response.setMessage("00");
                            response.getData().add(cInquiry);
                            return ResponseEntity.status(HttpStatus.OK).body(response);  
                        }
    
                ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>> response = new ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>();
                response.setStatus("00");
                response.setMessage("00");
                response.getData().add(cInquiry);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
                  
        }
}


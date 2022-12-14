// package com.test.demotest.controller;

// import java.util.Date;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.test.demotest.dto.RequestSubrogasi;
// import com.test.demotest.dto.ResponseData;
// import com.test.demotest.entity.Acs;
// import com.test.demotest.entity.T_Subrogasi;
// import com.test.demotest.entity.T_Subrogasi_Summary;
// import com.test.demotest.service.AcsService;
// import com.test.demotest.service.T_SubrogasiService;
// import com.test.demotest.service.T_SubrogasiSummaryService;

// @RestController
// @RequestMapping
// public class T_SubrogasiSummaryController {
    
//     @Autowired
//     private T_SubrogasiSummaryService subrogasiSummaryService;
//     @Autowired
//     private T_SubrogasiService subrogasiService;
//     @Autowired
//     private AcsService acsService;

//     @PostMapping("/api/v1/shs/subro/{noRekening}")
//     public ResponseEntity<ResponseData<T_Subrogasi_Summary>> create(@RequestBody RequestSubrogasi request, @PathVariable("noRekening") String noRekening){

//         try {
//             Acs acs = acsService.getByNoRekening(noRekening);

//             if(acs != null){

//                 T_Subrogasi_Summary subrogasiSummary = new T_Subrogasi_Summary();
//                 T_Subrogasi subrogasi= new T_Subrogasi();

//                 if(request.getCounterAngsuran() != subrogasiSummary.getLineNo()&& request.getCounterAngsuran()< subrogasiSummary.getLineNo()+1){

//                     if(acs.getAmtSubrogation()!=0){

//                         subrogasi.setNoRekening(noRekening);
//                         subrogasi.setNomorPeserta(noRekening);
//                         subrogasi.setNominalClaim(request.getNilaiRecoveries());
//                         subrogasi.setAkumulasiSubrogasi(acs.getAmtRecovery()+request.getNilaiRecoveries());
//                         subrogasi.setSisaKewajibanSubrogasi(acs.getAmtSubrogation());
//                         subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
//                         subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
//                         subrogasi.setPresentasePajak(Double.valueOf(0));
//                         subrogasi.setIdKlaim(UUID.randomUUID().toString());
//                         subrogasi.setStatus("0");

//                         subrogasiSummary.setLineNo(request.getCounterAngsuran());
//                         subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
//                         subrogasiSummary.setNominalSubrogasLebih(request.getNilaiRecoveries()-acs.getAmtSubrogation());
//                         subrogasiSummary.setNominalSubrogasiPokok(request.getNilaiRecoveries());
//                         subrogasiSummary.setJenisTransaksi(request.getJenisTransaksi());
//                         subrogasiSummary.setKodeBank(request.getKodeBank());
//                         subrogasiSummary.setKodeCabangAskrindo(request.getKodeCabangAskrindo());
//                         subrogasiSummary.setTglNotaKredit(new Date());
//                         subrogasiSummary.setRemark("RECOV"+request.getJenisTransaksi()+"2"+"_"+acs.getBpUnitCode()+noRekening+request.getCounterAngsuran());
//                         subrogasiSummary.setSubrogasiId(subrogasiService.save(subrogasi));
//                         subrogasiSummary.setNominalSubrogasiBunga(Double.valueOf(0));
//                         subrogasiSummary.setNominalSubrogasiDenda(Double.valueOf(0));
//                         subrogasiSummary.setNominalPajak(Double.valueOf(0));
//                         subrogasiSummary.setNominalFeeGross(Double.valueOf(0));
//                         subrogasiSummary.setNominalFeeNet(Double.valueOf(0));
//                         subrogasiSummary.setTanggalJurnal(null);
//                         subrogasiSummary.setNoJurnal(null);
//                         subrogasiSummary.setBiayaRekonsiliasi(Double.valueOf(0));
//                         subrogasiSummary.setCollectingAgent(null);
//                         subrogasiSummary.setNoNotaKredit(null);
//                         subrogasiSummary.setmRekeningGiro(null);
//                         subrogasiSummary.setIsNetting((byte) 0);
//                         subrogasiSummary.setStatus("0");

//                         ResponseData<T_Subrogasi_Summary> response = new ResponseData<T_Subrogasi_Summary>();
//                         response.setStatus("00");
//                         response.setMessage("00");
//                         response.getData().add(subrogasiSummaryService.save(subrogasiSummary));

//                         return ResponseEntity.status(HttpStatus.CREATED).body(response);  

//                     }
//                 }
//             }
//             throw new Exception("Data acs tidak ditemukan");
//         } catch (Exception e) {

//             ResponseData<T_Subrogasi_Summary> response = new ResponseData<T_Subrogasi_Summary>();
//             response.setStatus("00");
//             response.setMessage(e.getMessage());
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  
//         }        
//     }
// }

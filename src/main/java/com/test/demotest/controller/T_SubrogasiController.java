// package com.test.demotest.controller;

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
// import com.test.demotest.dto.ResponseDataSubrogasi;
// import com.test.demotest.entity.Acs;
// import com.test.demotest.entity.T_Subrogasi;
// import com.test.demotest.dto.ResponseData;
// import com.test.demotest.service.AcsService;
// import com.test.demotest.service.T_SubrogasiService;

// @RestController
// @RequestMapping("/api/v1/shs/subro")
// public class T_SubrogasiController {
   
//     @Autowired
//     private T_SubrogasiService subrogasiService;

//     @Autowired
//     private AcsService acsService;

//     @PostMapping("/{noRekening}")
//     public ResponseEntity<ResponseData<ResponseDataSubrogasi>> create(@RequestBody RequestSubrogasi request, @PathVariable("noRekening") String noRekening){
//         try {

//             Acs getDataAcs = acsService.getByNoRekening(noRekening);
//             if(getDataAcs != null){
               
//             }
//             T_Subrogasi subrogasi = new T_Subrogasi();
//             subrogasi.setNoRekening(request.getNoRekening());
//             subrogasi.setNomorPeserta(request.getNoRekening());
//             subrogasi.setNominalClaim(request.getNilaiRecoveries());
//             subrogasi.setIdKlaim(UUID.randomUUID().toString());
//             subrogasi.setPresentasiCoverage(Double.valueOf(request.getCovRatio()));
//             subrogasi.setPresentaseCollectingFee(Double.valueOf(0));
//             subrogasi.setPresentasePajak(Double.valueOf(0));
//             subrogasi.setStatus("0");
            


//             ResponseDataSubrogasi data = new ResponseDataSubrogasi();
//             data.setJenisTransaksi(request.getJenisTransaksi());
//             data.setNoPk(request.getNoPk());
//             data.setNoRekening(request.getNoRekening());
//             data.setRemark(request.getRemark());

//             ResponseData<ResponseDataSubrogasi> response = new ResponseData<ResponseDataSubrogasi>();
//             response.getData().add(data);
//             response.setMessage("00");
//             response.setStatus("00");

//             subrogasiService.save(subrogasi);
//             return ResponseEntity.status(HttpStatus.OK).body(response);
//         } catch (Exception e) {

//             ResponseData<ResponseDataSubrogasi> response = new ResponseData<ResponseDataSubrogasi>();
//             response.setMessage(e.getMessage());
//             response.setStatus("01");
//             return ResponseEntity.status(HttpStatus.OK).body(response);
            
//         }
//     }
    
// }

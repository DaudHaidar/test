package com.test.demotest.controller.acs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.dto.RequestSubrogasi;
import com.test.demotest.dto.ResponseData;
import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;
import com.test.demotest.service.acs.CLM_INQUIRY_SUBROGRATIONService;

@RestController
public class CLM_INQUIRY_SUBROGRATIONController {

    @Autowired
    private CLM_INQUIRY_SUBROGRATIONService cInquiryService;
    
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

    //ngetest get yang sudah lunas subrogationnya
    @GetMapping("/api/v1/shs/subro/notZero")
    public ResponseEntity<ResponseData<List<CLM_INQUIRY_SUBROGATION_CREDIT>>> getBelomLunas(Double sisa){
        sisa=1.0;
        List<CLM_INQUIRY_SUBROGATION_CREDIT> cInquiry =  cInquiryService.getByZeroSubrogration(sisa);

            if(cInquiry != null && sisa>0){
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

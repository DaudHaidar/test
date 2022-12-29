package com.askrindo.aossubrogasi.controller.acs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.askrindo.aossubrogasi.entity.acs.CLM_RECOV_PAYMENT;
import com.askrindo.aossubrogasi.service.acs.CLM_RECOV_PAYMENTService;

@RestController
public class CLM_RECOV_PAYMENTController {

    @Autowired
    private CLM_RECOV_PAYMENTService cRecovPaymentService;

    @DeleteMapping("/api/v1/shs/subro/recov/{id}")
    public void delete(@PathVariable("id") String id){
        cRecovPaymentService.delete(id);
    }

    @GetMapping("/api/v1/shs/subro/recov/created/{createdBy}")
    public List<CLM_RECOV_PAYMENT> getByCreated(@PathVariable("createdBy") String createdBy){
        return cRecovPaymentService.findByCreatedBy(createdBy);
    }
    
}

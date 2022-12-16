package com.test.demotest.controller.acs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.entity.acs.CLM_SETTLEMENT;
import com.test.demotest.service.acs.CLM_SETTLEMENTService;


@RestController
public class CLM_SETTLEMENTController {
    
    @Autowired
    private CLM_SETTLEMENTService cSettlementService;

    //delete berdasarkan SettleId di table claim settlement
    @DeleteMapping("/api/v1/shs/subro/settle/{id}")
    public void removeSettlement(@PathVariable("id") String id){
        cSettlementService.delete(id);
    }
}

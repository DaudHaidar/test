package com.test.demotest.controller.acs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.demotest.dto.ResponseData;
import com.test.demotest.entity.acs.CLM_SETTLEMENT_SUMMARY;
import com.test.demotest.service.acs.CLM_SETTLEMENT_SUMMARYService;

public class CLM_SETTLEMENT_SUMMARYController {

        @Autowired
        private CLM_SETTLEMENT_SUMMARYService cSettlementSummaryService;
        //cari berdasarkan yang createdBy di tabke CLM_SETTLEMENT_SUMMARY
        @GetMapping("/api/v1/shs/subro/{createdBy}")
        public ResponseEntity<ResponseData<List<CLM_SETTLEMENT_SUMMARY>>> findBCreated(@PathVariable("createdBy") String createdBy){
            List<CLM_SETTLEMENT_SUMMARY> search = cSettlementSummaryService.getByCreatedBy(createdBy);
            try {
                ResponseData<List<CLM_SETTLEMENT_SUMMARY>> response = new ResponseData<List<CLM_SETTLEMENT_SUMMARY>>();
                response.setMessage("00");
                response.setStatus("00");
                response.getData().add(search);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (Exception e) {
                ResponseData<List<CLM_SETTLEMENT_SUMMARY>> response = new ResponseData<List<CLM_SETTLEMENT_SUMMARY>>();
                response.setMessage(e.getMessage());
                response.setStatus("01");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
    
        //delete berdasarkan settleSummaryId di table claim summary settlement
        @DeleteMapping("/api/v1/shs/subro/summary/{id}")
        public void removeSettlementSummary(@PathVariable("id") String id){
            cSettlementSummaryService.delete(id);
        }   
}

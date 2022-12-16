package com.test.demotest.repository.acs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.acs.CLM_SETTLEMENT_SUMMARY;

public interface CLM_SETTLEMENT_SUMMARYRepository extends JpaRepository<CLM_SETTLEMENT_SUMMARY, String> {

    List<CLM_SETTLEMENT_SUMMARY >findByCreatedBy(String createdBy);
    
}

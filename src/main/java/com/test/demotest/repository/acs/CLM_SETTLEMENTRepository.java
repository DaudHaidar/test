package com.test.demotest.repository.acs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.acs.CLM_SETTLEMENT;

public interface CLM_SETTLEMENTRepository extends JpaRepository<CLM_SETTLEMENT, String> {

    List<CLM_SETTLEMENT> findByRegistrationId(String registrationId);
}

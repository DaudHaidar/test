package com.test.demotest.repository.acs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.acs.CLM_REGISTRATION_OS;

public interface CLM_REGISTRATION_OSRepository extends JpaRepository<CLM_REGISTRATION_OS,String> {
    
    CLM_REGISTRATION_OS findByRegistrationId(String registrationId);
}

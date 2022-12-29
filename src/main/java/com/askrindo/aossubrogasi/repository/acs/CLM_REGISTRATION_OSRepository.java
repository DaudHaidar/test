package com.askrindo.aossubrogasi.repository.acs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askrindo.aossubrogasi.entity.acs.CLM_REGISTRATION_OS;

public interface CLM_REGISTRATION_OSRepository extends JpaRepository<CLM_REGISTRATION_OS,String> {
    
    List<CLM_REGISTRATION_OS> findByRegistrationId(String registrationId);
}

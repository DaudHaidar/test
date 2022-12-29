package com.askrindo.aossubrogasi.repository.acs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.askrindo.aossubrogasi.entity.acs.CLM_PRELIMINARY;

public interface CLM_PRELIMINARYRepository extends JpaRepository<CLM_PRELIMINARY,String>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM CLAIM.CLM_PRELIMINARY WHERE REGISTRATION_ID = :registrationId")
    public CLM_PRELIMINARY findByRegistrationId(@Param("registrationId") String registrationId);

}

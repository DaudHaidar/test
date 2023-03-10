package com.askrindo.aossubrogasi.repository.acs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askrindo.aossubrogasi.entity.acs.CLM_RECOV_PAYMENT;

public interface CLM_RECOV_PAYMENTRepository extends JpaRepository<CLM_RECOV_PAYMENT,String>{
    
    List<CLM_RECOV_PAYMENT> findByCreatedBy(String createdBy);
}

package com.test.demotest.repository.aos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.aos.T_Subrogasi;

public interface T_SubrogasiRepository extends JpaRepository<T_Subrogasi, String> {

    T_Subrogasi findByNoRekening(String noRekening);
    
    
}

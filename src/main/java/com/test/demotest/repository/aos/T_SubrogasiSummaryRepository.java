package com.test.demotest.repository.aos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.aos.T_Subrogasi_Summary;

public interface T_SubrogasiSummaryRepository extends JpaRepository<T_Subrogasi_Summary,String> {

    T_Subrogasi_Summary findByLineNo(Integer counterAngsuran);
    
}

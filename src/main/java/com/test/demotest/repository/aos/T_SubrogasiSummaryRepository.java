package com.test.demotest.repository.aos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.demotest.entity.aos.T_Subrogasi_Summary;

public interface T_SubrogasiSummaryRepository extends JpaRepository<T_Subrogasi_Summary, String> {

    T_Subrogasi_Summary findByLineNo(Integer counterAngsuran);

    @Query(nativeQuery = true, value = "SELECT * FROM t_subrogasi_summary WHERE id_subrogasi = :subrogasiId")
    List<T_Subrogasi_Summary> findBySubrogasiId(String subrogasiId);

}

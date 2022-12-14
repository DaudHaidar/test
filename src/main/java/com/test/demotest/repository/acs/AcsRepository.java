package com.test.demotest.repository.acs;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.demotest.entity.acs.V_CLM_INQUIRY_SUBROGATION_CREDIT;

public interface AcsRepository extends JpaRepository<V_CLM_INQUIRY_SUBROGATION_CREDIT,String> {
    

    @Query(nativeQuery = true,value = "SELECT * FROM CLAIM.V_CLM_INQUIRY_SUBROGATION_CREDIT  WITH (NOLOCK) WHERE CREDIT_ACCOUNT_NO = :noRekening")
    // @Query("SELECT p FROM V_CLM_INQUIRY_SUBROGATION_CREDIT p WHERE p.creditAccountNo = :noRekening")
    public V_CLM_INQUIRY_SUBROGATION_CREDIT findClmQuery(@PathParam("noRekening") String noRekening);
}

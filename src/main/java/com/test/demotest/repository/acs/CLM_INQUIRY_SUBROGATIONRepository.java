package com.test.demotest.repository.acs;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.demotest.entity.acs.CLM_INQUIRY_SUBROGATION_CREDIT;

public interface CLM_INQUIRY_SUBROGATIONRepository extends JpaRepository<CLM_INQUIRY_SUBROGATION_CREDIT,String> {
    

    @Query(nativeQuery = true,value = "SELECT * FROM CLAIM.V_CLM_INQUIRY_SUBROGATION_CREDIT  WITH (NOLOCK) WHERE CREDIT_ACCOUNT_NO = :noRekening")
    // @Query("SELECT p FROM V_CLM_INQUIRY_SUBROGATION_CREDIT p WHERE p.creditAccountNo = :noRekening")
    public CLM_INQUIRY_SUBROGATION_CREDIT findClmQuery(@PathParam("noRekening") String noRekening);

    @Query(nativeQuery = true, value = "SELECT *FROM CLAIM.V_CLM_INQUIRY_SUBROGATION_CREDIT  WITH (NOLOCK) WHERE AMT_SUBROGRATION = :sisaSubrogration ")
    public List<CLM_INQUIRY_SUBROGATION_CREDIT> findClmSubrogration(@PathParam("sisaSubrogration") Double sisaSubrogration );
}

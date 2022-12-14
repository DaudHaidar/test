package com.test.demotest.repository;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.demotest.entity.Acs;

public interface AcsRepository extends JpaRepository<Acs,String> {
    
    @Query("SELECT p FROM Acs p WHERE p.creditAccountNo = :noRekening")
    Acs findClmQuery(@PathParam("noRekening") String noRekening);
}

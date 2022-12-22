package com.test.demotest.repository.aos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.aos.Logs;

public interface LogsRepository extends JpaRepository<Logs, Long> {
    
}

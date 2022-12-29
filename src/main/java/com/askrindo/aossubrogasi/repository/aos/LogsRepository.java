package com.askrindo.aossubrogasi.repository.aos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askrindo.aossubrogasi.entity.aos.Logs;

public interface LogsRepository extends JpaRepository<Logs, Long> {
    
}

package com.test.demotest.repository.aos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demotest.entity.aos.T_Token;

public interface T_TokenRepository  extends JpaRepository<T_Token, String>{
    String findByToken(String token);

    T_Token findByIdUser(String userId);
    
}

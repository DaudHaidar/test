package com.test.demotest.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.demotest.Jwt.JwtUtils;
import com.test.demotest.entitiy.T_Token;
import com.test.demotest.repository.T_TokenRepository;

@Service
public class T_TokenService {

    @Autowired
    private T_TokenRepository tokenRepo;
    @Autowired
    private JwtUtils jwtUtils;

    // T_Token token;

    public T_Token create(String token, String userId) {

        T_Token createToken = new T_Token();
        createToken.setToken(token);
        createToken.setIdUser(userId);
        createToken.setIsActive(1);
        createToken.setTokenExpired(jwtUtils.EXP);
        // createToken.setCreatedAt(new Date());
        return tokenRepo.save(createToken);

    }

    public T_Token update(String id, Integer exp) {
        if (tokenRepo.findById(id) == null) {
            throw new RuntimeException("id " + id + " tidak ditemukan");
        }
        T_Token currentToken = tokenRepo.findById(id).get();
        currentToken.setId(id);
        currentToken.setIsActive(exp);
        currentToken.setTokenExpired(exp);

        return tokenRepo.save(currentToken);
    }

    public void delete(String id){
        tokenRepo.deleteById(id);
    }

    public String searchToken(String token){
        return tokenRepo.findByToken(token);
    }

    public T_Token searchUserId(String userId){
        return tokenRepo.findByIdUser(userId);
    }


}

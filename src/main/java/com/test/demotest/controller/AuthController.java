package com.test.demotest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.demotest.Jwt.JwtUtils;
import com.test.demotest.dto.RequestLogin;
import com.test.demotest.dto.ResponseData;
import com.test.demotest.dto.ResponseTokenDTO;
import com.test.demotest.entitiy.M_User;
import com.test.demotest.entitiy.T_Token;
import com.test.demotest.service.T_TokenService;

@RestController

public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private T_TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseTokenDTO> signIn(@RequestBody RequestLogin requestLogin){
        try {   
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getUsername(), requestLogin.getPassword()));
            
            M_User userAccount = (M_User) auth.getPrincipal();

            T_Token searchUserId =  tokenService.searchUserId(userAccount.getId());


            if(searchUserId !=null){

                DecodedJWT jwt = JWT.decode(searchUserId.getToken());
    
                //checking token has expired or not
                if(jwt.getExpiresAt().before(new Date())){
                    String token = jwtUtils.generateToken(userAccount);
                    tokenService.delete(searchUserId.getId());
                    tokenService.create(token, userAccount.getId());
                    ResponseTokenDTO response = new ResponseTokenDTO();
                    response.setToken(token);
                            
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }else {
                    ResponseTokenDTO response = new ResponseTokenDTO();
                    response.setToken(searchUserId.getToken());
                    return ResponseEntity.status(HttpStatus.OK).body(response);
    
                }
        }

            String token = jwtUtils.generateToken(userAccount);
            tokenService.create(token, userAccount.getId());

            ResponseTokenDTO response = new ResponseTokenDTO();
            response.setToken(token);
            
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
         catch (Exception e) {
            System.out.println(e.getMessage());
            ResponseTokenDTO response = new ResponseTokenDTO();
            response.setToken(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    
}

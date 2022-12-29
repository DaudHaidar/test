package com.askrindo.aossubrogasi.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.askrindo.aossubrogasi.Jwt.JwtUtils;
import com.askrindo.aossubrogasi.dto.RequestLogin;
import com.askrindo.aossubrogasi.dto.ResponseData;
import com.askrindo.aossubrogasi.dto.ResponseDataToken;
import com.askrindo.aossubrogasi.entity.acs.CLM_SETTLEMENT_SUMMARY;
import com.askrindo.aossubrogasi.entity.aos.M_User;
import com.askrindo.aossubrogasi.entity.aos.T_Token;
import com.askrindo.aossubrogasi.service.aos.T_TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

@RestController

public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private T_TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseData<ResponseDataToken>> signIn(@RequestBody RequestLogin requestLogin){
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

                    ResponseDataToken responseToken = new ResponseDataToken();
                    responseToken.setToken(token);

                    ResponseData<ResponseDataToken> responseData = new ResponseData<ResponseDataToken>();
                    responseData.setStatus("00");
                    responseData.setMessage("00");
                    responseData.getData().add(responseToken);
                            
                    return ResponseEntity.status(HttpStatus.OK).body(responseData);
                }else {
                    ResponseDataToken responseToken = new ResponseDataToken();
                    responseToken.setToken(searchUserId.getToken());

                    ResponseData<ResponseDataToken>  responseData = new ResponseData<ResponseDataToken>();
                    responseData.setStatus("00");
                    responseData.setMessage("00");
                    responseData.getData().add(responseToken);

                    return ResponseEntity.status(HttpStatus.OK).body(responseData);
    
                }
        }

            String token = jwtUtils.generateToken(userAccount);
            tokenService.create(token, userAccount.getId());

            ResponseDataToken responseToken = new ResponseDataToken();
            responseToken.setToken(token);

            ResponseData<ResponseDataToken> responseData = new ResponseData<ResponseDataToken>();
            responseData.setStatus("00");
            responseData.setMessage("00");
            responseData.getData().add(responseToken);
            
            return ResponseEntity.status(HttpStatus.OK).body(responseData);

        }
         catch (Exception e) {

            ResponseData<ResponseDataToken> responseData = new ResponseData<ResponseDataToken>();
            responseData.setStatus("01");
            responseData.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    
}

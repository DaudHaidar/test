package com.askrindo.aossubrogasi.Jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.askrindo.aossubrogasi.service.aos.T_TokenService;

import io.jsonwebtoken.ExpiredJwtException;

public class JwtFilter extends GenericFilterBean {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private T_TokenService tokenService;

    public JwtFilter(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);


    public String ParseJwt(HttpServletRequest request){


        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            String jwt = bearerToken.substring(7, bearerToken.length());
            return jwt;
        }
        return null;
    }

    

    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String jwt = ParseJwt(httpServletRequest);
            // System.out.println("JWTTTTTT:" + jwt);

            if(StringUtils.hasText(jwt) ){
                if (this.jwtUtils.validateJwtToken(jwt)) {
                    Authentication authentication = this.jwtUtils.getAuthentication(jwt);
                    System.out.println(authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            
            chain.doFilter(request, response);   
            this.resetAuthenticationAfterRequest();

        } catch (ExpiredJwtException eje) {
            System.out.println("EJEEEEEE"+eje.hashCode());
            // tokenService.update(null, null, null, eje.hashCode());
            logger.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.debug("Exception " + eje.getMessage(), eje);
        }
       
    }

    
    private void resetAuthenticationAfterRequest() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
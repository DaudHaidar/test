package com.askrindo.aossubrogasi.Jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.askrindo.aossubrogasi.entity.aos.M_User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

    private  String jwtSecret = "secret";
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);  

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
    // private static final long EXPIRE_DURATION = 60000; // 1 menit
    public static final int EXP = (int) EXPIRE_DURATION;
    
    private static final String AUTHORITIES_KEY = "auth"; 
    
    public String generateToken(M_User userAccount){
        String authentication = userAccount.getAuthorities().stream().map(auth-> auth.getAuthority()).collect(Collectors.joining(","));
        
        return Jwts.builder()
        .setSubject(String.format("%s,%s", userAccount.getId(), userAccount.getUsername()))
        .claim("auth", authentication) .setIssuer("self")
        .setIssuedAt(new Date()).
        setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)).
        signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }

    public boolean validateJwtToken(String authToken){
        try {
             Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
             return true;
        } catch (MalformedJwtException e) {
            logger.error("Malformed Jwt :", e.getMessage());
        } catch(SignatureException e){
            logger.error("SignatureException :", e.getMessage());
        } catch(ExpiredJwtException e){
            logger.error("ExpiredJwtException Jwt :", e.getMessage());
        } catch(UnsupportedJwtException e){
            logger.error("UnsupportedJwtException Jwt :", e.getMessage());
        } catch (IllegalArgumentException e){
            logger.error("IllegalArgumentException Jwt :", e.getMessage());
        }
        return false;
    }

    public Authentication getAuthentication(String token) {

        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        

        Collection<? extends GrantedAuthority> authorities = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
                    .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
        
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}

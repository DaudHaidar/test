package com.test.demotest.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    
        if(currentUser.getName()!= "anonymousUser"){
            
            String x = currentUser.getName();
            String[] splitString = x.split(",");
            String username = splitString[1];
            return Optional.of(username);
        }
        
        return Optional.of("h2h-subro");
    }
    
}

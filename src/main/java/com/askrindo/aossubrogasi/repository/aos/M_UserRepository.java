package com.askrindo.aossubrogasi.repository.aos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askrindo.aossubrogasi.entity.aos.M_User;

public interface M_UserRepository extends JpaRepository<M_User,String> {
    M_User findByUsername(String username); 

} 


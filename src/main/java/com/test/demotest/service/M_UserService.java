package com.test.demotest.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.demotest.entitiy.M_User;
import com.test.demotest.repository.M_UserRepository;

@Service
public class M_UserService {

    @Autowired
    private M_UserRepository m_userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

   

    public M_UserService (PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    public M_User create(M_User m_user,  String request){
        
        if(request != null){
            m_user.setPassword(passwordEncoder.encode(m_user.getPassword()));
            return m_userRepo.save(m_user);
        }else{
            M_User guest = new M_User();
            guest.setIsActive(m_user.getIsActive());
            guest.setPassword(passwordEncoder.encode(m_user.getPassword()));
            guest.setUsername(m_user.getUsername());
            guest.setRoleId(m_user.getRoleId());
            return m_userRepo.save(guest);
        }


        
    }

    public M_User update(M_User m_user,String id){
        if(!(m_userRepo.findById(id).isPresent())){
            throw new RuntimeException("Id tidak ditemukan");
        }
        if(m_user.getId() != null){
            M_User currentUser = m_userRepo.findById(id).get();
            currentUser.setId(m_user.getId());
            currentUser.setUsername(m_user.getUsername());
            currentUser.setPassword(passwordEncoder.encode(m_user.getPassword()));
            currentUser.setIsActive(m_user.getIsActive());
            currentUser.setRoleId(m_user.getRoleId());
            
            return m_userRepo.save(currentUser);
        }
        // m_user.setPassword(passwordEncoder.encode(m_user.getPassword()));
        return null;
    }

    public Iterable<M_User> getAll(){
        return m_userRepo.findAll();
    }

    public void delete(String id){
        m_userRepo.deleteById(id);
    }

    public M_User searchByUsername(String username){
        return m_userRepo.findByUsername(username);
    }
    
}

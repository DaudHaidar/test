package com.askrindo.aossubrogasi.service.aos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.askrindo.aossubrogasi.entity.aos.M_User;
import com.askrindo.aossubrogasi.repository.aos.M_UserRepository;

@Service
public class M_UserService {

    @Autowired
    private M_UserRepository m_userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public M_UserService (PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    public M_User create(M_User m_user){
            if(m_userRepo.findByUsername(m_user.getUsername())!= null){
                throw new RuntimeException("Username sudah digunakan");
            }
            m_user.setPassword(passwordEncoder.encode(m_user.getPassword()));
            return m_userRepo.save(m_user);       
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

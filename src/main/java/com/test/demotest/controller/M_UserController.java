package com.test.demotest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.entitiy.M_User;
import com.test.demotest.service.M_UserService;

@RestController
@RequestMapping("/user")
public class M_UserController {
    
    @Autowired
    private M_UserService m_userService;

    @PostMapping
    public M_User save(@RequestBody M_User m_user,  HttpServletRequest request){

        if(request.getHeader("Authorization") != null){
            return m_userService.create(m_user, request.getHeader("Authorization"));
        }
        return m_userService.create(m_user,null);
    }   
    
    @PutMapping
    public M_User edit(@RequestBody M_User m_user){
        return m_userService.update(m_user, m_user.getId());
    }

    @GetMapping
    public Iterable<M_User> findAll(){
        return m_userService.getAll();
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") String id){
        m_userService.delete(id);
    }
}

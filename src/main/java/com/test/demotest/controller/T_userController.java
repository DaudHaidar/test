package com.test.demotest.controller;

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
import com.test.demotest.entitiy.T_Token;
import com.test.demotest.service.M_UserService;
import com.test.demotest.service.T_TokenService;


@RestController
@RequestMapping("/token")
public class T_userController {
    @Autowired
    private T_TokenService tokenService;
    @Autowired
    private M_UserService userService;

    @GetMapping
    private Iterable<M_User> findAll(){
        return userService.getAll();
    }
    
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") String id){
        tokenService.delete(id);
    }


}

package com.test.demotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demotest.dto.ResponseData;
import com.test.demotest.dto.ResponseDataUser;
import com.test.demotest.entity.M_User;
import com.test.demotest.service.M_UserService;

@RestController
@RequestMapping("/user")
public class M_UserController {
    
    @Autowired
    private M_UserService m_userService;

    @PostMapping
    public ResponseEntity<ResponseData<ResponseDataUser>> save(@RequestBody M_User m_user){
        try {

            m_userService.create(m_user);

            ResponseDataUser responseUser = new ResponseDataUser(); 
            responseUser.setId(m_user.getId());
            responseUser.setIsActive(m_user.getIsActive());
            responseUser.setRoleId(m_user.getRoleId());
            responseUser.setUsername(m_user.getUsername());

            ResponseData<ResponseDataUser> response = new ResponseData<ResponseDataUser>();
            response.setStatus("00");
            response.setMessage("00");
            response.getData().add(responseUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
           ResponseData<ResponseDataUser> response = new ResponseData<ResponseDataUser>();
            response.setStatus("00");
            response.setMessage("00");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

    }   
    
    @PutMapping
    public  ResponseEntity<ResponseData<ResponseDataUser>> edit(@RequestBody M_User m_user){
        m_userService.update(m_user,m_user.getId());

        ResponseDataUser responseUser = new ResponseDataUser(); 
        responseUser.setId(m_user.getId());
        responseUser.setIsActive(m_user.getIsActive());
        responseUser.setRoleId(m_user.getRoleId());
        responseUser.setUsername(m_user.getUsername());

        ResponseData<ResponseDataUser> response = new ResponseData<ResponseDataUser>();
        response.setStatus("00");
        response.setMessage("00");
        response.getData().add(responseUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<M_User>>> findAll(){
        try {
            ResponseData<Iterable<M_User>> response = new ResponseData<Iterable<M_User>>();
            response.setStatus("00");
            response.setMessage("00");
            response.getData().add(m_userService.getAll());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") String id){
        try {
             m_userService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
    }
}

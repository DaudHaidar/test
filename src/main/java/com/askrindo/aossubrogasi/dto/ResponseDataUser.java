package com.askrindo.aossubrogasi.dto;

import com.askrindo.aossubrogasi.entity.aos.Role;

public class ResponseDataUser {
    private String id;
    private String username;
    private Role roleId;
    private Integer isActive;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Role getRoleId() {
        return roleId;
    }
    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    
    
    
   
    
}

package com.test.demotest.entity.aos;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "M_User")
public class M_User extends BaseEnity<String> implements UserDetails  {
    
    @Id
    @Column(name="id",unique = true)
    private String id = UUID.randomUUID().toString();

    @Column(name="username",unique = true, nullable = false,length = 30)
    private String username;
    @Column(name="password", nullable = false,length = 225)
    private String password;
    @Column(name="role_id", nullable = false)
    private Role roleId;
    @Column(name="is_active", nullable = false)
    private Integer isActive;



    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> auth = new HashSet<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("ROLE "+roleId));
        return auth;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }





    
}

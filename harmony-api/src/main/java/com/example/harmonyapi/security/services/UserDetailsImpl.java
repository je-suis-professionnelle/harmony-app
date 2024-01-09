package com.example.harmonyapi.security.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.harmonyapi.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(
                user.getPseudo(),
                user.getMdp());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
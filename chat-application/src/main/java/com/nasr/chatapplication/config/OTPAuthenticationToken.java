package com.nasr.chatapplication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
public class OTPAuthenticationToken implements Authentication {

    private final String phoneNumber;

    private final Collection<GrantedAuthority> authorities;
    private Object detail;

    public OTPAuthenticationToken(String phoneNumber,Collection<GrantedAuthority> authorities) {
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.detail;
    }

    @Override
    public Object getPrincipal() {
        return this.phoneNumber;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.phoneNumber;
    }
}

package com.nasr.chatapplication.service;

import com.nasr.chatapplication.domain.Role;

import java.util.Collection;

public interface RoleService {

    long count();

    void saveAll(Collection<Role> roles);
}

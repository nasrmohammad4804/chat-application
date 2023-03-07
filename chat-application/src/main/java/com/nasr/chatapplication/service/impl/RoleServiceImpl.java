package com.nasr.chatapplication.service.impl;

import com.nasr.chatapplication.domain.Role;
import com.nasr.chatapplication.repository.RoleRepository;
import com.nasr.chatapplication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public void saveAll(Collection<Role> roles) {
        repository.saveAll(roles);
    }
}

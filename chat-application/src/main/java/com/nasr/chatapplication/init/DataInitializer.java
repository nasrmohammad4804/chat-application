package com.nasr.chatapplication.init;

import com.nasr.chatapplication.domain.Role;
import com.nasr.chatapplication.domain.User;
import com.nasr.chatapplication.service.RoleService;
import com.nasr.chatapplication.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        initDefaultRoles();
        initDefaultUsers();
    }

    private void initDefaultRoles() {
        if (roleService.count() != 0) return;

        List<Role> roles = List.of(
                new Role("ADMIN"),
                new Role("USER")
        );

        roleService.saveAll(roles);

    }

    private void initDefaultUsers() {

        if (userService.count() != 0) return;

        List<User> users = List.of(
                new User("mohammad", "nasr", "09031261399", null),
                new User("javad", "zare", "09900974359", null)
        );

        userService.saveAll(users);
    }
}

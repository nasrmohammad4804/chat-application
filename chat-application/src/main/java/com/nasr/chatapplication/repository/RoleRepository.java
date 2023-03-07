package com.nasr.chatapplication.repository;

import com.nasr.chatapplication.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

}

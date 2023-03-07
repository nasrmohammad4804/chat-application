package com.nasr.chatapplication.repository;

import com.nasr.chatapplication.domain.User;
import com.nasr.chatapplication.model.response.UserResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select new com.nasr.chatapplication.model.response.UserResponseDto(u.firstName,u.lastName,u.phoneNumber,u.avatar)" +
            " from User as u where u.phoneNumber=:phoneNumber")
    Optional<UserResponseDto> getUserByPhoneNumber(String phoneNumber);

    boolean existsUserByPhoneNumber(String phoneNumber);
}

package com.nasr.chatapplication.service;

import com.nasr.chatapplication.domain.User;
import com.nasr.chatapplication.model.request.UserRegistrationRequestDto;
import com.nasr.chatapplication.model.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserResponseDto> getUserByPhoneNumber(String phoneNumber);

    long count();

    void  saveAll(List<User> users);

    UserResponseDto save(UserRegistrationRequestDto registrationRequest);
}

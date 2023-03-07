package com.nasr.chatapplication.controller;

import com.nasr.chatapplication.model.request.UserRegistrationRequestDto;
import com.nasr.chatapplication.model.response.UserResponseDto;
import com.nasr.chatapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@Valid UserRegistrationRequestDto registrationRequest) {
        log.info("user registration request is received ");
        UserResponseDto dto = userService.save(registrationRequest);
        return ResponseEntity.ok(dto);
    }
}

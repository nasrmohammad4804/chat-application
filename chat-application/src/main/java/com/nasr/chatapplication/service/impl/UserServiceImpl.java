package com.nasr.chatapplication.service.impl;

import com.nasr.chatapplication.domain.User;
import com.nasr.chatapplication.exception.IllegalRegistrationException;
import com.nasr.chatapplication.model.request.UserRegistrationRequestDto;
import com.nasr.chatapplication.model.response.UserResponseDto;
import com.nasr.chatapplication.repository.UserRepository;
import com.nasr.chatapplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Optional<UserResponseDto> getUserByPhoneNumber(String phoneNumber) {
        return repository.getUserByPhoneNumber(phoneNumber);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }

    @Override
    @Transactional
    public UserResponseDto save(UserRegistrationRequestDto registrationRequest) {

        boolean existsUserByPhoneNumber = repository.existsUserByPhoneNumber(registrationRequest.getPhoneNumber());

        if (existsUserByPhoneNumber)
            throw new IllegalRegistrationException("user with this phoneNumber already exists");

        User user = mapper.map(registrationRequest, User.class);

        return mapper.map(repository.save(user), UserResponseDto.class);
    }
}

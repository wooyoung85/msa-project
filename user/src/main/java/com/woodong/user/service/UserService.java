package com.woodong.user.service;

import com.woodong.user.data.dto.UserDto;
import com.woodong.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    void createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    List<UserEntity> getUserByAll();
    UserDto getUserDetailsByEmail(String email);
}

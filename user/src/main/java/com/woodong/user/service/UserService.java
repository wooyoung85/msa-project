package com.woodong.user.service;

import com.woodong.user.dto.UserDto;
import com.woodong.user.entity.UserEntity;

import java.util.List;


public interface UserService {
    void createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    List<UserEntity> getUserByAll();
}

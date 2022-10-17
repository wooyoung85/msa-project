package com.woodong.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private LocalDateTime createdDt;
    private String encryptedPwd;
}

package com.woodong.user.data.dto;

import com.woodong.user.data.response.ResponseOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private LocalDateTime createdDt;
    private String encryptedPwd;
    private List<ResponseOrder> orders;
}

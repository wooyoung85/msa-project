package com.woodong.user.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, unique = true)
    private String encryptedPwd;
}

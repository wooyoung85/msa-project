package com.woodong.user.controller;

import com.woodong.user.dto.UserDto;
import com.woodong.user.entity.UserEntity;
import com.woodong.user.request.UserRequest;
import com.woodong.user.response.ResponseUser;
import com.woodong.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final UserService userService;

    @Value("${greeting.message}")
    private String message;

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        List<UserEntity> userList = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(user -> result.add(new ModelMapper().map(user, ResponseUser.class)));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody UserRequest userRequest) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(userRequest, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's working in User Service on Port %s", request.getServerPort());
    }

    @GetMapping("/welcome")
    public String welcome() {
        return message;
    }
}

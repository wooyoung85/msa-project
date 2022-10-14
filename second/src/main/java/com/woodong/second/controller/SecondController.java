package com.woodong.second.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class SecondController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second Service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader(value = "second-request", required = false) String header){
        System.out.println(header);
        return "Hello World in Second Service";
    }
}

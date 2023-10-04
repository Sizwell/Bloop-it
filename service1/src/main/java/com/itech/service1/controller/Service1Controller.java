package com.itech.service1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Service1Controller {

    @GetMapping("/sayHello")
    public String sayHello()
    {
        return "Hello from Service 1";
    }
}

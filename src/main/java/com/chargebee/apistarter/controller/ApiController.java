package com.chargebee.apistarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
public class ApiController {

    @GetMapping("/")
    public String index() {
        System.out.println("Hello World");
        return "Greetings from " + UUID.randomUUID();
    }

}
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Hello world!";
    }

    @GetMapping("/private")
    public String privateHome(Principal principal) {
        return "Private "+ principal.getName() +"!";
    }
}

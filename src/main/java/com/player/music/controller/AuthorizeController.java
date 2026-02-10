package com.player.music.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register() {
        return "Register";
    }
}

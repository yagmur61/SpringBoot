package com.eticaret.musteri.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class YetkiController {

    @GetMapping("/giris")
    public String login() {
        return "login";
    }
}


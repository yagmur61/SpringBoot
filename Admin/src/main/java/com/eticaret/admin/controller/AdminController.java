package com.eticaret.admin.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {
    @GetMapping("/kategoriler")
    public String categories(Model model) {
        model.addAttribute("title", "Kategoriler");
        return "categories";
    }
}

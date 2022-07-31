package com.eticaret.musteri.controller;

import com.eticaret.kutuphane.dto.UrunDto;
import com.eticaret.kutuphane.model.Category;
import com.eticaret.kutuphane.service.CategoryService;
import com.eticaret.kutuphane.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AnasayfaController {

    @Autowired
    private UrunService urunService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String anasayfa(Model model){
        return "anasayfa";
    }

    @GetMapping("/anasayfa")
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        List<UrunDto>urunDtos = urunService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("uruns", urunDtos);
        return "index";
    }
}

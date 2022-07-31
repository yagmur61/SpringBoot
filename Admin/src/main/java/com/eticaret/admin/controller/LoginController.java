package com.eticaret.admin.controller;

import com.eticaret.kutuphane.dto.AdminDto;
import com.eticaret.kutuphane.model.Admin;
import com.eticaret.kutuphane.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/giris")
    public String loginForm(Model model) {
        model.addAttribute("title", "Giriş");
        return "login";
    }

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("title", "AnaSayfa");
        return "index";
    }

    @GetMapping("/kayitol")
    public String kayitol(Model model) {
        model.addAttribute("title", "Kayıt Ol");
        model.addAttribute("adminDto", new AdminDto());
        return "kayitol";
    }

    @GetMapping("/sifremiunuttum")
    public String sifremiUnuttum(Model model) {
        model.addAttribute("title", "Şifremi Unuttum");
        return "sifremi-unuttum";
    }

    @PostMapping("/yeni-kayit")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto,
                              BindingResult result,
                              Model model) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "kayitol";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDto", adminDto);
                System.out.println("Kullanıcı Sistemde Kayıtlı!");
                model.addAttribute("emailError", "Girdiğiniz e-mail adresi zaten kayıtlı!");
                return "kayitol";
            }

if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
    adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
    adminService.save(adminDto);
    System.out.println("Başarılı");
    model.addAttribute("başarılı", "Kayıt başarıyla oluşturuldu.");
    model.addAttribute("adminDto", adminDto);
}else{
    model.addAttribute("adminDto", adminDto);
    model.addAttribute("passwordError", "Şifreniz yanlış! Lütfen,Kontrol ediniz." );
    System.out.println("Şifreler aynı değil!");
    return "kayitol";
}


    }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors","Sunucu Hatası! Lütfen daha sonra tekrar deneyiniz.");

    }
        return "kayitol";

}


}




































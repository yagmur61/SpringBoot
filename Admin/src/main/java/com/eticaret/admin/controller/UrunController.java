package com.eticaret.admin.controller;

import com.eticaret.kutuphane.dto.UrunDto;
import com.eticaret.kutuphane.model.Category;
import com.eticaret.kutuphane.model.Urun;
import com.eticaret.kutuphane.service.CategoryService;
import com.eticaret.kutuphane.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class UrunController {
    @Autowired
    private UrunService urunService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/uruns")
    public String uruns(Model model, Principal principal){
        if (principal == null){
            return "redirect:/giris";
        }
        List<UrunDto> urunDtoList = urunService.findAll();
        model.addAttribute("title", "Ürünleri Yönet");
        model.addAttribute("uruns", urunDtoList);
        model.addAttribute("size", urunDtoList.size());
        return "uruns";
 }

    @GetMapping("/uruns/{sayfaNo}")
    public String urunsSayfa(@PathVariable("sayfaNo") int sayfaNo, Model model, Principal principal){
        if(principal == null){
            return "redirect:/giris";
        }
        Page<UrunDto> uruns = urunService.urunsSayfasi(sayfaNo);
        model.addAttribute("title", "Ürünleri Yönet");
        model.addAttribute("size", uruns.getSize());
        model.addAttribute("toplamSayfalar", uruns.getTotalPages());
        model.addAttribute("gecerliSayfa", sayfaNo);
        model.addAttribute("uruns", uruns);
        return "uruns";
    }


    @GetMapping("/arama-sonucu/{sayfaNo}")
    public String araUruns(@PathVariable("sayfaNo")int sayfaNo,
                                 @RequestParam("keyword") String keyword,
                                 Model model,
                                 Principal principal){
        if(principal == null){
            return "redirect:/giris";
        }
        Page<UrunDto> uruns = urunService.araUruns(sayfaNo, keyword);
        model.addAttribute("title", "\n" +
                "Arama sonuçları");
        model.addAttribute("uruns", uruns);
        model.addAttribute("size", uruns.getSize());
        model.addAttribute("gecerliSayfa", sayfaNo);
        model.addAttribute("toplamSayfalar", uruns.getTotalPages());
        return "sonuc-uruns";
    }


    @GetMapping("/ekle-urun")
    public String addUrunForm(Model model, Principal principal){
        if(principal == null){
            return "redirect:/giris";
        }
        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("title", "Ürün Ekle");
        model.addAttribute("categories", categories);
        model.addAttribute("urun", new UrunDto());
        return "ekle-urun";
    }

    @PostMapping("/kaydet-urun")
    public String saveUrun(@ModelAttribute("urun")UrunDto urunDto,
                              @RequestParam("urunResmi") MultipartFile urunResmi,
                              RedirectAttributes attributes) {
        try {
            urunService.save(urunResmi, urunDto);
            attributes.addFlashAttribute("basarili", "Ürün Eklendi!");
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("hatali", "Ürün Ekleme  Başarısız!");
        }
        return "redirect:/uruns";
    }


    @GetMapping("/guncelle-urun/{id}")
    public String guncelleUrunFormu(@PathVariable("id") Long id, Model model, Principal principal){
        if (principal == null){
            return "redirect:/giris";
        }
       model.addAttribute("title", "\n" +
               "Ürünleri Güncelle");
      List<Category> categories = categoryService.findAllByActivated();
        UrunDto urunDto = urunService.getById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("urunDto", urunDto);
        return "guncelle-urun";
    }


    @PostMapping("/guncelle-urun/{id}")
    public String guncellemeİslemi(@PathVariable("id") Long id,
                                @ModelAttribute("urunDto") UrunDto urunDto,
                                @RequestParam("urunResmi")MultipartFile urunResmi,
                                RedirectAttributes attributes
    ){
        try {
            urunService.update(urunResmi, urunDto);
            attributes.addFlashAttribute("basarili", "Ürün Güncellendi!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("hatali", "Ürün Güncelleme Başarısız!");
        }
        return "redirect:/uruns";

    }
    @RequestMapping(value = "/etkin-urun/{id}", method = {RequestMethod.PUT , RequestMethod.GET})
    public String etkinUrun(@PathVariable("id")Long id, RedirectAttributes attributes){
        try {
            urunService.enableById(id);
            attributes.addFlashAttribute("basarili", "Ürün Etkinleştirildi.!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("hatali", "Ürün Etkinleştirilmesi Başarısız!");
        }
        return "redirect:/uruns";
    }

    @RequestMapping(value = "/sil-urun/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public String silUrun(@PathVariable("id") Long id, RedirectAttributes attributes){
        try {
            urunService.deleteById(id);
            attributes.addFlashAttribute("basarili", "Ürün Silindi!");
        }catch (Exception e){
            e.printStackTrace();
            attributes.addFlashAttribute("hatali", "Ürün Silinemedi!");
        }
        return "redirect:/uruns";
    }
}



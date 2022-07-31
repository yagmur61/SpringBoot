package com.eticaret.kutuphane.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ResimYukle {
    private final String UPLOAD_FOLDER = "C:\\Users\\a\\IdeaProjects\\Springboot\\Admin\\src\\main\\resources\\static\\img\\urun-resim";

    public boolean yukleResim(MultipartFile urunResmi) {
        boolean isUpload = false;
        try {
            Files.copy(urunResmi.getInputStream(),
                    Paths.get(UPLOAD_FOLDER + File.separator, urunResmi.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpload;
    }

    public boolean konrolEt(MultipartFile urunResmi){
        boolean kontrol = false;
        try{
            File file = new File(UPLOAD_FOLDER + "\\" + urunResmi.getOriginalFilename());
            kontrol = file.exists();

        }catch (Exception e){
            e.printStackTrace();
        }
        return kontrol;
    }

}

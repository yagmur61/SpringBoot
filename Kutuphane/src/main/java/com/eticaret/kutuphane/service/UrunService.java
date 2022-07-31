package com.eticaret.kutuphane.service;

import com.eticaret.kutuphane.dto.UrunDto;
import com.eticaret.kutuphane.model.Urun;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UrunService {
    List<UrunDto> findAll();
    Urun save (MultipartFile urunResmi, UrunDto urunDto);
    Urun update(MultipartFile urunResmi, UrunDto urunDto);
    void deleteById(Long id);
    void enableById(Long id);
    UrunDto getById(Long id);

    Page<UrunDto> urunsSayfasi(int sayfaNo);

    Page<UrunDto> araUruns(int sayfaNo, String keyword);
}

package com.eticaret.kutuphane.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    @Size(min = 3, max = 10, message = "\n" +
            "Adınız Geçersiz!(Adınız 3-10 karakter arasında olmalıdır.)")
    private String adi;
    @Size(min = 2, max = 15, message = "Soyadınız Geçersiz!(Soyadınız 2-15 karakter arasında olmalıdır.)")
    private String soyadi;

    private String username;
    @Size(min = 5, max = 15, message = "Şifreniz Geçersiz!(Şifreniz 5-15 karakter arasında olmalıdır.)")
    private String password;

    private String repeatPassword;
}

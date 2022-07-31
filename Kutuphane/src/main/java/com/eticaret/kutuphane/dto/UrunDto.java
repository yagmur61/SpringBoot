package com.eticaret.kutuphane.dto;

import com.eticaret.kutuphane.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrunDto {
    private Long id;
    private String name;
    private String aciklama;
    private double maliyetFiyati;
    private double satisFiyati;
    private int miktar;
    private Category category;
    private String image;
    private boolean activated;
    private boolean deleted;
}

package com.eticaret.kutuphane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sepet")

public class Sepet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siparis_detay_id")
    private Long id;
    private int adet;
    private double toplamFiyat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "alisveris_sepeti_id", referencedColumnName = "alisveris_sepeti_id" )
    private AlısverisSepeti alısverisSepeti;

 @OneToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "urun_id", referencedColumnName = "urun_id")
    private Urun urun;

}

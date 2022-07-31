package com.eticaret.kutuphane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "siparis_detay")
public class SiparisDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siparis_detay_id" )
    private Long id;

    private int adet;
    private double toplamFiyat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siparis_id", referencedColumnName = "siparis_id")
    private Siparis siparis;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "urun_id", referencedColumnName = "urun_id")
    private  Urun urun;
}

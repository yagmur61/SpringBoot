package com.eticaret.kutuphane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sipariss")

public class Siparis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siparis_id")

    private Long id;
    private Date siparisTarihi;
    private Date teslimTarihi;
    private double toplamFiyat;
    private double kargoUcreti;
    private String siparisDurumu;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "musteri_id", referencedColumnName = "musteri_id")
    private Musteri musteri;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "siparis")
    private List<SiparisDetay> siparisDetayList;
}

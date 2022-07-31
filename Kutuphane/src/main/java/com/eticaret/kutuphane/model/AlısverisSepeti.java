package com.eticaret.kutuphane.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alisveris_sepeti")

public class AlısverisSepeti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alisveris_sepeti_id")
    private Long id;
    private int toplamUrunAdet;
    private double toplamFiyatlar;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "musteri_id", referencedColumnName = "musteri_id" )
    private Musteri musteri;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alısverisSepeti")
    private Set<Sepet> sepet;
}

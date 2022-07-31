package com.eticaret.kutuphane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "musteris", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "image","m_telNo"}))
public class Musteri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "musteri_id")

    private Long id;
    private String m_adi;
    private String m_soyadi;
    private String username;
    private String m_sehir;

    @Column(name = "m_telNo")
    private String m_telNo;
    private String m_adres;
    private String password;
    @Lob
    @Column(name = "image" , columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "sehir")
    private String sehir;


    @OneToOne(mappedBy = "musteri")
    private AlısverisSepeti alısverisSepeti;
    @OneToMany(mappedBy = "musteri")
    private List<Siparis> sipariss;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "musteris_roles" ,
                   joinColumns = @JoinColumn(name = "musteri_id", referencedColumnName = "musteri_id" ),
                     inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "role_id"))
    private Collection<Role> roles;


}

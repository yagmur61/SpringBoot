package com.eticaret.kutuphane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sehirs")

public class Sehir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sehir_id")
    private Long id;
    private String s_adi;
}

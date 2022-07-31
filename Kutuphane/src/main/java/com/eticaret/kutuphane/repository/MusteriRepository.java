package com.eticaret.kutuphane.repository;

import com.eticaret.kutuphane.model.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Long> {

    Musteri findByUsername(String  username);
}

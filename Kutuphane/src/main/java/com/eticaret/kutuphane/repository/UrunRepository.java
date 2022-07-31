package com.eticaret.kutuphane.repository;

import com.eticaret.kutuphane.model.Urun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UrunRepository extends JpaRepository<Urun, Long> {
    @Query("select p from Urun p")
    Page<Urun> urunSayfasi(Pageable pageable);

  @Query("select p from Urun p where p.aciklama like %?1% or p.name like %?1%")
  Page<Urun> araUruns(String keyword, Pageable pageable);

   @Query("select p from Urun p where p.aciklama like %?1% or p.name like %?1%")
   List<Urun> araUrunsList(String keyword);
}

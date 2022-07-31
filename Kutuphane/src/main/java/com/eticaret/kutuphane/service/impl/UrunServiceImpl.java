package com.eticaret.kutuphane.service.impl;

import com.eticaret.kutuphane.dto.UrunDto;
import com.eticaret.kutuphane.model.Urun;
import com.eticaret.kutuphane.repository.UrunRepository;
import com.eticaret.kutuphane.service.UrunService;
import com.eticaret.kutuphane.utils.ResimYukle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UrunServiceImpl implements UrunService {
    @Autowired
    private UrunRepository urunRepository;

    @Autowired
    private ResimYukle resimYukle;


    @Override
    public List<UrunDto> findAll() {
        List<Urun>uruns = urunRepository.findAll();
        List<UrunDto> urunDtoList= transfer(uruns);
        return urunDtoList;
    }

    @Override
    public Urun save(MultipartFile urunResmi, UrunDto urunDto) {
        try {
            Urun urun = new Urun();
            if (urunResmi == null) {
                urun.setImage(null);
            } else {
               if(resimYukle.yukleResim(urunResmi)){
                   System.out.println("Yükleme Başarılı");
               }
                urun.setImage(Base64.getEncoder().encodeToString(urunResmi.getBytes()));
            }
            urun.setName(urunDto.getName());
            urun.setAciklama(urunDto.getAciklama());
            urun.setCategory(urunDto.getCategory());
            urun.setMaliyetFiyati(urunDto.getMaliyetFiyati());
            urun.setMiktar(urunDto.getMiktar());
            urun.set_activated(true);
            urun.set_deleted(false);
            return urunRepository.save(urun);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public Urun update( MultipartFile urunResmi, UrunDto urunDto) {
        try {
            Urun urun = urunRepository.getById(urunDto.getId());
            if (urunResmi == null) {
                urun.setImage(urun.getImage());
            } else {
                 if (resimYukle.konrolEt(urunResmi)== false){

                     resimYukle.yukleResim(urunResmi);
                 }
                System.out.println(" Ürün Güncellendi");
                urun.setImage(Base64.getEncoder().encodeToString(urunResmi.getBytes()));

            }
            urun.setName(urunDto.getName());
            urun.setAciklama(urunDto.getAciklama());
            urun.setSatisFiyati(urunDto.getSatisFiyati());
            urun.setMaliyetFiyati(urunDto.getMaliyetFiyati());
            urun.setMiktar(urunDto.getMiktar());
            urun.setCategory(urunDto.getCategory());
            return urunRepository.save(urun);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public void deleteById(Long id) {
        Urun urun = urunRepository.getById(id);
        urun.set_deleted(true);
        urun.set_activated(false);
        urunRepository.save(urun);

    }

    @Override
    public void enableById(Long id) {
        Urun urun = urunRepository.getById(id);
        urun.set_activated(true);
        urun.set_deleted(false);
        urunRepository.save(urun);

    }

    @Override
    public UrunDto getById(Long id) {
        Urun urun= urunRepository.getById(id);
        UrunDto urunDto= new UrunDto();
        urunDto.setId(urun.getId());
        urunDto.setName(urun.getName());
        urunDto.setAciklama(urun.getAciklama());
        urunDto.setMiktar(urun.getMiktar());
        urunDto.setCategory(urun.getCategory());
        urunDto.setSatisFiyati(urun.getSatisFiyati());
        urunDto.setMaliyetFiyati(urun.getMaliyetFiyati());
        urunDto.setImage(urun.getImage());
        urunDto.setDeleted(urun.is_deleted());
        urunDto.setActivated(urun.is_activated());

        return urunDto;
    }

    @Override
    public Page<UrunDto> urunsSayfasi(int sayfaNo) {
        Pageable pageable = PageRequest.of(sayfaNo, 5);
        List<UrunDto> uruns = transfer(urunRepository.findAll());
        Page<UrunDto> urunSayfalari = toPage(uruns,pageable);
        return urunSayfalari;
    }



    @Override
    public Page<UrunDto> araUruns(int sayfaNo, String keyword) {
        Pageable pageable = PageRequest.of(sayfaNo, 5);
        List<UrunDto> urunDtoList = transfer(urunRepository.araUrunsList(keyword));
        Page<UrunDto> uruns  = toPage(urunDtoList, pageable);
        return uruns;
    }

    private  Page toPage(List<UrunDto> list , Pageable pageable){
        if (pageable.getOffset() >= list.size()){
            return  Page.empty();
        }
        int baslatIndex= (int) pageable.getOffset();
        int bitirIndex = ((pageable.getOffset()+ pageable.getPageSize()) > list.size())
                ?  list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(baslatIndex, bitirIndex);
        return  new PageImpl(subList,pageable, list.size());
    }

    private List<UrunDto> transfer (List<Urun> uruns){
        List<UrunDto> urunDtoList = new ArrayList<>();
        for (Urun urun : uruns){
            UrunDto urunDto = new UrunDto();
            urunDto.setId(urun.getId());
            urunDto.setName(urun.getName());
            urunDto.setAciklama(urun.getAciklama());
            urunDto.setMiktar(urun.getMiktar());
            urunDto.setCategory(urun.getCategory());
            urunDto.setSatisFiyati(urun.getSatisFiyati());
            urunDto.setMaliyetFiyati(urun.getMaliyetFiyati());
            urunDto.setImage(urun.getImage());
            urunDto.setDeleted(urun.is_deleted());
            urunDto.setActivated(urun.is_activated());
            urunDtoList.add(urunDto);
        }
        return urunDtoList;
      }
    }



package com.eticaret.musteri.config;

import com.eticaret.kutuphane.model.Musteri;
import com.eticaret.kutuphane.repository.MusteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class MusteriServiceConfig implements UserDetailsService {
    @Autowired
    private MusteriRepository musteriRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Musteri musteri = musteriRepository.findByUsername(username);
        if(musteri == null){
            throw new UsernameNotFoundException("Kullanıcı Bulunamadı!");
        }
        return new User(musteri.getUsername(),
                musteri.getPassword(),
                musteri.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}

package com.eticaret.kutuphane.service.impl;

import com.eticaret.kutuphane.dto.AdminDto;
import com.eticaret.kutuphane.model.Admin;
import com.eticaret.kutuphane.repository.AdminRepository;
import com.eticaret.kutuphane.repository.RoleRepository;
import com.eticaret.kutuphane.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setAdi(adminDto.getAdi());
        admin.setSoyadi(adminDto.getSoyadi());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}

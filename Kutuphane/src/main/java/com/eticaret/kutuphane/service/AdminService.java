package com.eticaret.kutuphane.service;


import com.eticaret.kutuphane.dto.AdminDto;
import com.eticaret.kutuphane.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
}

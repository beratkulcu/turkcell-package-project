package com.turkcell.service;

import com.turkcell.entity.Admin;
import com.turkcell.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AdminService {

    private final AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {

        adminRepository.save(admin);
    }
}

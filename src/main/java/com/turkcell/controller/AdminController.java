package com.turkcell.controller;


import com.turkcell.entity.Admin;
import com.turkcell.entity.Role;
import com.turkcell.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("general")
@RequiredArgsConstructor

public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<Boolean> adminLogin (@RequestBody Admin admin){
        admin.setRole(Role.ADMIN);
        adminService.saveAdmin(admin);
        return ResponseEntity.ok(true);
    }

}

package com.turkcell.controller;

import com.turkcell.dto.PackagesDTO;
import com.turkcell.entity.Admin;
import com.turkcell.entity.Packages;
import com.turkcell.entity.Role;
import com.turkcell.entity.User;
import com.turkcell.exception.IllegalLogin;
import com.turkcell.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
public class PackagesController {

    private final PackageService packageService;

    //Paket Kayıt Etme

    @PostMapping("/save")
    public ResponseEntity<String> savePackages(@RequestBody Packages packages) {
        packageService.savePackages(packages);
        return ResponseEntity.ok().body("Paket Başarı ile kayıt edildi ");
    }

    //Paket Görüntüleme
    @GetMapping ("/getAll")
    public  ResponseEntity<List<PackagesDTO>> getAllPackages(){
        return  ResponseEntity.ok(packageService.findAllPackages());
    }
}

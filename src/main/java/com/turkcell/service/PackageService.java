package com.turkcell.service;

import com.turkcell.dto.PackagesDTO;
import com.turkcell.entity.Packages;
import com.turkcell.entity.User;
import com.turkcell.exception.PackageNotFoundException;
import com.turkcell.exception.UserNotFoundException;
import com.turkcell.repository.PackagesRepository;
import com.turkcell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService {


    @Autowired
    private PackagesRepository packagesRepository;

    @Autowired
    private UserRepository userRepository;

    public void savePackages(Packages packages) {

        packagesRepository.save(packages);
    }




    public Packages findById(Long packageId) {
        return packagesRepository.findById(packageId).orElseThrow(null);
    }

    public void purchasePackage(Long userId, Long packageId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Kullanıcı bulunamadı"));
        Packages packages = packagesRepository.findById(packageId).orElseThrow(
                () -> new PackageNotFoundException("Paket bulunamadı"));
    }


    private  PackagesDTO packagesDTOchange (Packages packages){
        PackagesDTO packagesDTO = new PackagesDTO();
        packagesDTO.setPackageName(packages.getPackageName());
        packagesDTO.setSms(packages.getSms());
        packagesDTO.setMinute(packages.getMinute());
        packagesDTO.setInternet(packages.getInternet());
        packagesDTO.setPrice(packages.getPrice());
        return  packagesDTO;
    }


    public List<PackagesDTO> findAllPackages() {
        List<Packages> packagesList = packagesRepository.findAll();
        List<PackagesDTO> packagesDTOS = packagesList
                .stream().map(this::packagesDTOchange).collect(Collectors.toList());
        return packagesDTOS;
    }


}

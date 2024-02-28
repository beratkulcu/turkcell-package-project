package com.turkcell.Mapper;

import com.turkcell.dto.PackagesDTO;
import com.turkcell.entity.Packages;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper {

    public PackagesDTO packageToPackageDto(Packages packages){
        PackagesDTO packagesDTO = new PackagesDTO();
        packagesDTO.setPrice(packages.getPrice());
        packagesDTO.setMinute(String.valueOf(packages.getPrice()));
        packagesDTO.setInternet(packages.getInternet());
        packagesDTO.setSms(packages.getSms());
        packagesDTO.setPackageName(packages.getPackageName());
        return packagesDTO;
    }
}

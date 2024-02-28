package com.turkcell.controller;

import com.turkcell.Mapper.UserMapper;
import com.turkcell.dto.PackagesDTO;
import com.turkcell.dto.UserDTO;
import com.turkcell.entity.Packages;
import com.turkcell.entity.Role;
import com.turkcell.entity.User;
import com.turkcell.exception.PackageAvailableException;
import com.turkcell.service.PackageService;
import com.turkcell.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping ("Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final PackageService packageService;

    private final UserMapper userMapper;

    @PostMapping("/add")
    public ResponseEntity<String> savePackages(@RequestBody User user){
        user.setRole(Role.USER);
        userService.addUser(user);
        return ResponseEntity.ok().body
                (" Başarı ile kayıt olundu , Turkcell'in ayrıcalıklı Dünyasına Hoşgeldin  :)  " +
                                "\n"       + user.getName() + "\n"
                + user.getSurname() + "\n"
                + user.getPhoneNumber() + "\n"
                + user.getEmail() + "\n"
                );
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        List<UserDTO> userDTOList = userList.stream()
                .map(userMapper::userDtoChange)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        User user = userService.findByUserId(id);
        return ResponseEntity.ok(userMapper.userDtoChange(user));
    }

    @PostMapping ("/{userId}/paraYukleme")
    public ResponseEntity<String> hesabaPara (@PathVariable Long userId , @RequestParam("balance") Long balance){

        userService.hesabaParaYukleme(userId,balance);
        return  ResponseEntity.ok("Başarı ile hesabınıza para aktarıldı ...");
    }


    @PostMapping ("/purchase/{userId}/{packagesId}")
    public ResponseEntity<String> purchasePackage (@PathVariable Long userId, @PathVariable Long packagesId){
        //return ResponseEntity.ok().body(userService.purchasePackage(userId, packagesId));

        User user = userService.findByUserId(userId);
        Packages packages = packageService.findById(packagesId);

        if (user == null) {
            //throw new UserNotFoundException("bulunamadı");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı bulunamadı");
        }
        if (packages == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paket Bulunamadı");
        }
        if (user.getBalance() < packages.getPrice()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Yetersiz bakiye yükleme yapınız");
        }

        if (!user.getPackages().isEmpty()){
            throw  new PackageAvailableException("Kullanıcının Zaten paketi mevcut");
        }

        List<Packages> packagesList = new ArrayList<>();
        packagesList.add(packages);
        // hesaptan düştü
        user.setBalance(user.getBalance() - packages.getPrice());
        user.setPackages(packagesList);
        userService.saveUser(user);

        return  ResponseEntity.ok().body("Paket eklendi");
    }

    @DeleteMapping ("/delete/{userId}/packages/{packagesId}")
    public ResponseEntity<PackagesDTO> paketSilme (@PathVariable Long userId , @PathVariable Long packagesId){
        return  ResponseEntity.ok().body(userService.deletePackages(userId, packagesId ));
    }

    @PutMapping ("/update/{userid}")
    public ResponseEntity<UserDTO> kullaniciGuncelleme (@PathVariable Long userid , @RequestBody UserDTO userDTO){
        User updatedUser = userService.findByUpdateId(userid , userDTO);
        return  ResponseEntity.ok(userMapper.userDtoChange(updatedUser));
    }
}

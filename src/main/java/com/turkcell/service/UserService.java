package com.turkcell.service;

import com.turkcell.Mapper.PackageMapper;
import com.turkcell.controller.PackagesController;
import com.turkcell.dto.PackagesDTO;
import com.turkcell.dto.UserDTO;
import com.turkcell.entity.Packages;
import com.turkcell.entity.User;
import com.turkcell.exception.*;
import com.turkcell.repository.PackagesRepository;
import com.turkcell.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PackagesRepository packagesRepository;

    private final PackageMapper packageMapper;

    private final  PackageService packageService;

    private  final PackagesController packagesController;


    public void addUser(User user) {

        userRepository.save(user);


    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    public User findByUserId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserNotFound"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void hesabaParaYukleme(Long userId, Long balance) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Kullanıcı Bulunamadı") );



        Long aktifBakiye = user.getBalance();
        user.setBalance(aktifBakiye + balance);

        userRepository.save(user);
    }

    public PackagesDTO deletePackages(Long userId, Long packagesId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı"));
        Packages packages = packagesRepository.findById(packagesId)
                .orElseThrow(() -> new PackageNotFoundException("Paket bulunamadı"));

        if (!user.getPackages().contains(packages)) {
            throw new UserPackageNotFoundException("Kullanıcı bu paketi bulundurmuyor.");
        }

        Long paketÜcreti = packages.getPrice();
        Double taahhüt = paketÜcreti * 0.25;

        Long kullanıcıBakiyesi = user.getBalance();

        if (user.getBalance() >= taahhüt){
            user.setBalance((long) (kullanıcıBakiyesi - taahhüt));

        }

        user.getPackages().remove(packages);
        userRepository.save(user);

        return packageMapper.packageToPackageDto(packages);
    }

    @Transactional
    public User findByUpdateId(Long userid , UserDTO userDTO) {

        User user  = userRepository.findById(userid)
                .orElseThrow(()-> new UserNotFoundException("Kullanıcı Bulunamadı !!"));


                user.setName(userDTO.getName());
                user.setSurname(userDTO.getSurname());
                user.setEmail(userDTO.getEmail());
                user.setPhoneNumber(userDTO.getPhoneNumber());

              return  userRepository.save(user);

    }
}

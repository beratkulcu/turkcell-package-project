package com.turkcell.dto;

import com.turkcell.entity.Packages;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    @Size(min = 3 , max = 25 , message = "Kural dışı isim girişi !! ")
    private String name;

    @NotBlank
    private String surname;


    @NotBlank (message = "Telefon numarası boş bırakılamaz")
    @Column(unique = true )
    private String phoneNumber;


    @NotBlank (message = "E-mail adresi boş bırakılamaz")
    @Size (min = 11)
    @Column (unique = true)
    private String email;

    private Long balance;

    private List<Packages> packages = new ArrayList<>();
}

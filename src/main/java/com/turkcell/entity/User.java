package com.turkcell.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 25, message = "Kural dışı isim girişi !! ")
    private String name;

    @NotBlank
    private String surname;

    @NotBlank(message = "Telefon numarası boş bırakılamaz")
    @Column(unique = true)
    private String phoneNumber;

    @NotBlank(message = "E-mail adresi boş bırakılamaz")
    @Size(min = 11)
    @Column(unique = true)
    private String email;

    private Long balance;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Packages> packages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;
}

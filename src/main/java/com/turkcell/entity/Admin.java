package com.turkcell.entity;


import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private  Long id;

    private String userName;

    private String password;

    @Enumerated (EnumType.STRING)
    private Role role;
}

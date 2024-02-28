package com.turkcell.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Packages {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;

    private String packageName;
    private Long price ;
    private String sms;
    private String minute;
    private String internet;
}

package com.turkcell.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PackagesDTO {
    private String packageName;

    private Long price;

    private String sms;

    private String minute;

    private String internet;
}

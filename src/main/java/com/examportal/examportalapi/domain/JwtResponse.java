package com.examportal.examportalapi.domain;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {

    private String token;
}
package com.examportal.examportalapi.data.transfer.object;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private int userId;
    private String firstName;
    private String lastName;
    private String username ;
    private String email;
    private String password;
    private boolean isEnabled = true;

    private Set<UserRoleDto> userRoles = new HashSet<>();

}

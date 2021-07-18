package com.examportal.examportalapi.data.transfer.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;
    private String firstName;
    private String lastName;
    private String userName =firstName + lastName;;
    private String email;
    private String password;
    private boolean isEnabled = true;

    private Set<UserRoleDto> userRoles = new HashSet<>();

}

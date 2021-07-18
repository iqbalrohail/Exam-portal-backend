package com.examportal.examportalapi.data.transfer.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private int userRoleid;
    private UserDto user ;
    private RoleDto role ;

}

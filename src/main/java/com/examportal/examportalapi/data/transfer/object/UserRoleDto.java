package com.examportal.examportalapi.data.transfer.object;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRoleDto {

    private int userRoleid;
    private UserDto user ;
    private RoleDto role ;

}

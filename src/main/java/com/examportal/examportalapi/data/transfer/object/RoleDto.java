package com.examportal.examportalapi.data.transfer.object;

import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDto {

    private int roleId;
    private String name;
    private Set<UserRoleDto> userRoles = new HashSet<>();
}

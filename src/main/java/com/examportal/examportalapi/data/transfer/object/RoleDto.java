package com.examportal.examportalapi.data.transfer.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private int roleId;
    private String name;
    private Set<UserRoleDto> userRoles = new HashSet<>();
}

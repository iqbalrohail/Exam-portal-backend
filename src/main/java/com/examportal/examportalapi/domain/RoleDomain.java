package com.examportal.examportalapi.domain;

import com.examportal.examportalapi.data.transfer.object.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleDomain")
    private Set<UserRoleDomain> userRoleDomains = new HashSet<>();
}

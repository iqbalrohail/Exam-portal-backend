package com.examportal.examportalapi.domain;

import com.examportal.examportalapi.data.transfer.object.UserRoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String userName = firstName + lastName;
    private String email;
    private String password;
    private boolean isEnabled = true;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "userDomain")
    @JsonIgnore
    private Set<UserRoleDomain> userRoleDomains = new HashSet<>();
}

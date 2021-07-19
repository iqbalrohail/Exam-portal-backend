package com.examportal.examportalapi.domain;

import com.examportal.examportalapi.data.transfer.object.RoleDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userRoleid;

    @ManyToOne(cascade =CascadeType.ALL ,  fetch = FetchType.EAGER)
    private UserDomain userDomain ;

    @ManyToOne
    private RoleDomain roleDomain ;
}

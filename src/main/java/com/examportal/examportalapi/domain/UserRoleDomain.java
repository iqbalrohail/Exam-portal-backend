package com.examportal.examportalapi.domain;

import com.examportal.examportalapi.data.transfer.object.RoleDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userDomain_userId")
    private UserDomain userDomain ;

    @ManyToOne
    private RoleDomain roleDomain ;
}

package com.examportal.examportalapi.domain;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UserRoleDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleid;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserDomain userDomain ;

    @ManyToOne
    private RoleDomain roleDomain ;
}

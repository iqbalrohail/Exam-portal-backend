package com.examportal.examportalapi.domain;

import com.examportal.examportalapi.data.transfer.object.UserRoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class UserDomain implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean isEnabled = true;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "userDomain")
    @JsonIgnore
    private Set<UserRoleDomain> userRoleDomains = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Authority> authorities = new HashSet<>();
        userRoleDomains.forEach(userRoleDomain -> {
            authorities.add(new Authority(userRoleDomain.getRoleDomain().getName()));
        });

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

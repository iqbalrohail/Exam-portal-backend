package com.examportal.examportalapi.service;

import com.examportal.examportalapi.data.transfer.object.RoleDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import com.examportal.examportalapi.domain.RoleDomain;
import com.examportal.examportalapi.domain.UserDomain;
import com.examportal.examportalapi.domain.UserRoleDomain;
import com.examportal.examportalapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private UserService userService;

    public Set<UserRoleDomain> add(UserDomain userDomain) {
        Set<UserRoleDomain> userRoles = new HashSet<>();
        RoleDomain roleDomain = new RoleDomain();
        roleDomain.setName("NORMAL");
        UserRoleDomain userRoleDomain = new UserRoleDomain();
        userRoleDomain.setUserDomain(userDomain);
        userRoleDomain.setRoleDomain(roleDomain);

        userRoles.add(userRoleDomain);
        return userRoles;

    }
}

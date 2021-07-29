package com.examportal.examportalapi.controller;

import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import com.examportal.examportalapi.domain.UserDomain;
import com.examportal.examportalapi.domain.UserRoleDomain;
import com.examportal.examportalapi.service.RoleService;
import com.examportal.examportalapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public MessageDto addUser(@RequestBody UserDto userDto)
    {
        log.info("Post call have been received at user/add with DTO " + userDto);
                                             //Set<UserRoleDomain>
        UserDomain userDomain = userService.mapUserDtoToDomain(userDto);
        return userService.add(userDomain, roleService.add(userDomain));
    }

    @GetMapping("/get/{userName}")
    public UserDto getUsers(@PathVariable("userName") String userName)
    {
        log.info("Get call have been received at user/get/ "+userName);
        return userService.getById(userName);
    }

}

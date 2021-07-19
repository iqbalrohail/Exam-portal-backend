package com.examportal.examportalapi.service;

import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import com.examportal.examportalapi.domain.UserDomain;
import com.examportal.examportalapi.domain.UserRoleDomain;
import com.examportal.examportalapi.repository.RoleRepository;
import com.examportal.examportalapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public MessageDto add(UserDto userDto , Set<UserRoleDomain> userRoles)
    {
             UserDomain userDomain = mapUserDtoToDomain(userDto);
            if( userRepository.findByUserName(userDomain.getUserName())!=null)
            {
                String responseMessage = "User is already register with name ! "+userDomain.getUserName();
                MessageDto messageDto = new MessageDto(responseMessage);
                log.info(responseMessage);
                return messageDto;
            }
            else {

                for (UserRoleDomain userRoleDomain: userRoles) {
                    roleRepository.saveAndFlush(userRoleDomain.getRoleDomain());
                }

                userDomain.getUserRoleDomains().addAll(userRoles);
                userRepository.saveAndFlush(userDomain);
                String responseMessage = "User has been added with Id " + userDomain.getUserId();
                MessageDto messageDto = new MessageDto(responseMessage);
                log.info(responseMessage);
                return messageDto;
            }
    }

    public UserDomain mapUserDtoToDomain(UserDto userDto)
    {
        return objectMapper.convertValue(userDto , UserDomain.class);
    }

    public UserDto mapUserDomainToDto(UserDomain userDomain)
    {
        return objectMapper.convertValue(userDomain , UserDto.class);
    }

}

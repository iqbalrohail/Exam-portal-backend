package com.examportal.examportalapi.service;

import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import com.examportal.examportalapi.domain.UserDomain;
import com.examportal.examportalapi.domain.UserRoleDomain;
import com.examportal.examportalapi.repository.RoleRepository;
import com.examportal.examportalapi.repository.UserRepository;
import com.examportal.examportalapi.repository.UserRoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public MessageDto add(UserDomain userDomain , Set<UserRoleDomain> userRoles)
    {
            // UserDomain userDomain = mapUserDtoToDomain(userDto);
            if( userRepository.findByUserName(userDomain.getUserName())!=null)
            {
                String responseMessage = "User is already register with name ! "+userDomain.getUserName();
                MessageDto messageDto = new MessageDto(responseMessage);
                log.info(responseMessage);
                return messageDto;
            }
            else {

                for (UserRoleDomain userRoleDomain: userRoles) {
                    System.out.println("result : "+userRoleDomain.getRoleDomain());
                    roleRepository.save(userRoleDomain.getRoleDomain());
                }

                userDomain.getUserRoleDomains().addAll(userRoles);
                userRepository.save(userDomain);
                String responseMessage = "User has been added with Id " + userDomain.getUserId();
                MessageDto messageDto = new MessageDto(responseMessage);
                log.info(responseMessage);
                return messageDto;
            }
    }

    public UserDto getById(String userName)
    {
       UserDomain userDomain =  userRepository.findByUserName(userName);
       return mapUserDomainToDto(userDomain);
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

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
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

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MessageDto add(UserDomain userDomain, Set<UserRoleDomain> userRoles) {
        // UserDomain userDomain = mapUserDtoToDomain(userDto);
        if (userRepository.findByUsername(userDomain.getUsername()) != null) {
            String responseMessage = "User is already register with name ! " + userDomain.getUsername();
            MessageDto messageDto = new MessageDto(responseMessage);
            log.info(responseMessage);
            return messageDto;
        } else {
            userDomain.setPassword(bCryptPasswordEncoder.encode(userDomain.getPassword()));

            for (UserRoleDomain userRoleDomain : userRoles) {
                System.out.println("result : " + userRoleDomain.getRoleDomain());
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

    public UserDto getByUserName(String userName) {
        UserDomain userDomain = userRepository.findByUsername(userName);
        return mapUserDomainToDto(userDomain);
    }

    public List<UserDomain> getAll() {
        return userRepository.findAll();
    }

    public MessageDto deleteUsers(String userName) {
        UserDomain userDomain = userRepository.findByUsername(userName);

        if (userDomain != null) {
            userRepository.delete(userDomain);
            String responseMessage = "User have been deleted with User Name " + userName;
            MessageDto messageDto = new MessageDto(responseMessage);
            return messageDto;

        } else {
            String responseMessage = "User cannot be found with User Name " + userName;
            MessageDto messageDto = new MessageDto(responseMessage);
            return messageDto;

        }
    }

    public MessageDto updateUsers(UserDto userDto, int id) {

        if (userRepository.findById(id).isPresent()) {
            userDto.setUserId(id);
            userRepository.saveAndFlush(mapUserDtoToDomain(userDto));
            String responseMessage = "User have been updated with User Name " + userDto.getUsername();
            MessageDto messageDto = new MessageDto(responseMessage);
            return messageDto;

        } else {
            String responseMessage = "User cannot be found with User Name " + userDto.getUsername();
            MessageDto messageDto = new MessageDto(responseMessage);
            return messageDto;
        }

    }

    public UserDomain mapUserDtoToDomain(UserDto userDto) {
        return objectMapper.convertValue(userDto, UserDomain.class);
    }

    public UserDto mapUserDomainToDto(UserDomain userDomain) {
        return objectMapper.convertValue(userDomain, UserDto.class);
    }

}

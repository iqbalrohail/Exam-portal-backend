package com.examportal.examportalapi.service;

import com.examportal.examportalapi.controller.UserController;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.data.transfer.object.UserDto;
import com.examportal.examportalapi.domain.UserDomain;
import com.examportal.examportalapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    public MessageDto add(UserDto userDto)
    {
            UserDomain userDomain = mapUserDtoToDomain(userDto);
            userRepository.saveAndFlush(userDomain);
            String responseMessage = "User has been added with Id" + userDomain.getUserId();
            MessageDto messageDto = new MessageDto(responseMessage);
            log.info(responseMessage);
            return messageDto;
    }

    private UserDomain mapUserDtoToDomain(UserDto userDto)
    {
        return objectMapper.convertValue(userDto , UserDomain.class);
    }

    private UserDto mapUserDomainToDto(UserDomain userDomain)
    {
        return objectMapper.convertValue(userDomain , UserDto.class);
    }

}

package com.examportal.examportalapi.service;

import com.examportal.examportalapi.domain.UserDomain;
import com.examportal.examportalapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserDomain userDomain = userRepository.findByUserName(userName);

        if(userDomain == null)
        {
            throw new UsernameNotFoundException("User cannot be found with User Name "+userName);
        }
        return userDomain;
    }
}

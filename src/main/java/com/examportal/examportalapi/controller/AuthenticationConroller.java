package com.examportal.examportalapi.controller;

import com.examportal.examportalapi.service.JwtUtil;
import com.examportal.examportalapi.domain.JwtRequest;
import com.examportal.examportalapi.domain.JwtResponse;
import com.examportal.examportalapi.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationConroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authentication(jwtRequest.getUsername(), jwtRequest.getPassword());

        } catch (UsernameNotFoundException e) {
            throw new Exception("Username cannot be found !");

        }

        UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String generatedToken = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(generatedToken));

    }

    private void authentication(String username, String password) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("User is currently disabled !");

        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials !");
        }

    }


}

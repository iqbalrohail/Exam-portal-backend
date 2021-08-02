package com.examportal.examportalapi.service;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader("Authorization");
        System.out.println(token);

        String username = null;
        String jwtToken = null;

        if (token != null && token.startsWith("Bearer ")) {

            jwtToken = token.substring(7);
            try
            {
                username = jwtUtil.extractUsername(jwtToken);

            }catch (ExpiredJwtException e)
            {
                log.info("Expired token !");

            }catch (Exception e)
            {
                e.printStackTrace();
                log.info("Error in the token !");
            }

        } else {
            log.info("Invalid Token ! , Token must start with bearer string");
        }

        // Validating Token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            log.info("Invalid Token !");
        }

        filterChain.doFilter(request, response);
    }
}

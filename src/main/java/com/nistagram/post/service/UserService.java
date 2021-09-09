package com.nistagram.post.service;

import com.nistagram.post.config.JwtTokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JwtTokenUtil jwtTokenUtil;

    public UserService(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String getUsername() {
        String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
        String jwtToken = token.substring(7);
        return jwtTokenUtil.getUsernameFromToken(jwtToken);
    }
}

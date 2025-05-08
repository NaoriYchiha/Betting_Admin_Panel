package com.lba.docker.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken {

    public JwtAuthentication(CustomUserDetails userDetails) {
        super(userDetails, null, Collections.singletonList(new SimpleGrantedAuthority(userDetails.getAuthorities().toString())));
    }
}

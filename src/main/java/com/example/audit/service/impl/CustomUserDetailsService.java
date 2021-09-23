package com.example.audit.service.impl;

import com.example.audit.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AdminService securityService;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return securityService.findByUsername(username);
    }
}

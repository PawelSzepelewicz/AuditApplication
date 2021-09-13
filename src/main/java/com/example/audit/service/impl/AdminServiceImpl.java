package com.example.audit.service.impl;

import com.example.audit.core.entity.Admin;
import com.example.audit.exception.ForbiddenException;
import com.example.audit.repository.AdminRepository;
import com.example.audit.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;

    @Override
    public Admin findByUsername(final String username) {
        return repository.findByUsername(username).orElseThrow(ForbiddenException::new);
    }
}

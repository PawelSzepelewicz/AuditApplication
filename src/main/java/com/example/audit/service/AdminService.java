package com.example.audit.service;

import com.example.audit.core.entity.Admin;

public interface AdminService {
    Admin findByUsername(String username);
}

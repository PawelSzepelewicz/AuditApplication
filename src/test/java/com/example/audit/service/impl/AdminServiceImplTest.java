package com.example.audit.service.impl;

import com.example.audit.core.entity.Admin;
import com.example.audit.exception.ForbiddenException;
import com.example.audit.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private AdminRepository repository;
    @InjectMocks
    private AdminServiceImpl service;

    @Test
    void findByUsername() {
        var admin = new Admin("Admin", "$2a$12$LV62Vehuivc.rtG8e5AO3uGQNv5.tMEZy0NrKEQ2GAja2tK1p1EcO");
        when(repository.findByUsername("Admin")).thenReturn(Optional.of(admin));
        assertEquals(admin, service.findByUsername("Admin"));
        assertThrows(ForbiddenException.class, () -> service.findByUsername("WrongAdmin"));
    }
}

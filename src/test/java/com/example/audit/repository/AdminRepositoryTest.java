package com.example.audit.repository;

import com.example.audit.core.entity.Admin;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminRepositoryTest extends RepositoryTest {
    @Autowired
    private AdminRepository repository;
    @Spy
    private final PasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Test
    void findByUsername() {
        String username = "Admin";
        Admin admin = repository.findByUsername(username).orElseThrow();
        assertEquals(admin.getUsername(), username);
        assertTrue(encoder.matches("@Developer1609", admin.getPassword()));
    }
}

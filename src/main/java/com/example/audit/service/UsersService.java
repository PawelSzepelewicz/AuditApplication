package com.example.audit.service;

import com.example.audit.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<User> findByUsername(String username);

    User saveNewUserIfNotExist(String username);

    List<User> getListOfUsers();

    User getById(Long id);
}

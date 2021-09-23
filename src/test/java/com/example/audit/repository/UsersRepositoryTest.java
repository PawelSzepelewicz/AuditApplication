package com.example.audit.repository;

import com.example.audit.core.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersRepositoryTest extends RepositoryTest {
    @Autowired
    private UsersRepository repository;

    @Test
    void findUserByUsername() {
        String username = "Admin";
        Optional<User> user = repository.findUserByUsername(username);
        assertTrue(user.isPresent());
        assertEquals(username, user.orElseThrow().getUsername());
        assertEquals(repository.findUserByUsername("emptyUsername"), Optional.empty());
    }

    @Test
    void findAllByOrderById() {
        List<User> usersList = repository.findAllByOrderById();
        List<Long> testList = new ArrayList<>();
        usersList.forEach(user -> testList.add(user.getId()));
        assertEquals(testList, testList.stream().sorted().collect(Collectors.toList()));
    }
}

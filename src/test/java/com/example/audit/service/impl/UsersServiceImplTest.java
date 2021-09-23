package com.example.audit.service.impl;

import com.example.audit.core.entity.User;
import com.example.audit.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    @Mock
    private UsersRepository repository;
    @InjectMocks
    private UsersServiceImpl service;

    @Test
    void findByUsername() {
        var user = new User("Username");
        Mockito.when(repository.findUserByUsername("Username")).thenReturn(Optional.of(user));
        assertEquals(service.findByUsername("Username").orElseThrow().getUsername(), "Username");
    }

    @Test
    void saveNewUserIfNotExist() {
        var user = new User("Username");
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);
        Mockito.when(service.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        service.saveNewUserIfNotExist(user.getUsername());
        Mockito.verify(repository).save(user);
    }

    @Test
    void doNotSaveWhenUserExist() {
        var user = new User("Username");
        Mockito.when(service.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        service.saveNewUserIfNotExist(user.getUsername());
        Mockito.verify(repository, Mockito.only()).findUserByUsername(user.getUsername());
    }

    @Test
    void getListOfUsers() {
        List<User> userList = List.of(new User("First"), new User("Second"), new User("Third"));
        Mockito.when(repository.findAllByOrderById()).thenReturn(userList);
        assertEquals(service.getListOfUsers(), userList);
    }

    @Test
    void getById() {
        var user = new User("Username");
        user.setId(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.empty());
        assertEquals(service.getById(1L), user);
        assertThrows(EntityNotFoundException.class, () ->  service.getById(2L));
    }
}

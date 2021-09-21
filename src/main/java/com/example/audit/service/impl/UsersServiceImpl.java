package com.example.audit.service.impl;

import com.example.audit.core.entity.User;
import com.example.audit.repository.UsersRepository;
import com.example.audit.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository repository;

    @Override
    public Optional<User> findByUsername(final String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public User saveNewUserIfNotExist(final String username) {
        final var user = findByUsername(username);

        if (user.isEmpty()) {
            return repository.save(new User(username));
        } else {
            return user.get();
        }
    }

    @Override
    public List<User> getListOfUsers() {
        return repository.findAllByOrderById();
    }

    @Override
    public User getById(final Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}

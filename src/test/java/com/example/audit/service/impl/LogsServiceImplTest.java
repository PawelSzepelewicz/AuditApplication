package com.example.audit.service.impl;

import com.example.audit.core.dto.LogDto;
import com.example.audit.core.dto.LogInfoDto;
import com.example.audit.core.entity.Log;
import com.example.audit.core.entity.User;
import com.example.audit.repository.LogsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogsServiceImplTest {
    @Mock
    private LogsRepository repository;
    @Mock
    private UsersServiceImpl usersService;
    @InjectMocks
    private LogsServiceImpl service;

    @Test
    void createLog() {
        var subjectUser = new User("Subject");
        var objectUser = new User("Object");
        when(usersService.saveNewUserIfNotExist("Subject")).thenReturn(subjectUser);
        when(usersService.saveNewUserIfNotExist("Object")).thenReturn(objectUser);
        var logDto = new LogDto("Object",  "Subject", "Registered", LocalDateTime.now());
        service.createLog(logDto);
        verify(usersService, Mockito.times(1)).saveNewUserIfNotExist("Subject");
        verify(repository).save(new Log("Registered", subjectUser, objectUser, logDto.getActionTime()));
    }

    @Test
     void createLogWithNullUser() {
        var subject = new User("Subject");
        var logDto = new LogDto(null, "Subject", "Registered", LocalDateTime.now());
        when(usersService.saveNewUserIfNotExist("Subject")).thenReturn(subject);
        service.createLog(logDto);
        verify(usersService, Mockito.never()).saveNewUserIfNotExist("Object");
        verify(usersService).saveNewUserIfNotExist("Subject");
        verify(repository).save(new Log("Registered", subject, null, logDto.getActionTime()));
    }

    @Test
    void getLogsByUserId() {
        var time = LocalDateTime.now();
        var subject = new User("Subject");
        Log logA = new Log("Logging in", subject, null, time);
        Log logB = new Log("Registered", subject, new User("Object"), LocalDateTime.now());
        LogInfoDto logInfoA = new LogInfoDto("Subject", "Logging in", null,
                time.getMonth().name().toLowerCase(), time.getDayOfMonth(), time.getHour(), time.getMinute());
        LogInfoDto logInfoB = new LogInfoDto("Subject", "Registered", "Object",
                time.getMonth().name().toLowerCase(), time.getDayOfMonth(), time.getHour(), time.getMinute());
        when(repository.getUsersLogs(1L)).thenReturn(List.of(logA, logB));
        assertEquals(List.of(logInfoA, logInfoB), service.getLogsByUserId(1L));
    }
}

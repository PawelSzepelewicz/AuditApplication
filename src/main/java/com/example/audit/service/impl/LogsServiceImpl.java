package com.example.audit.service.impl;

import com.example.audit.core.dto.LogDto;
import com.example.audit.core.dto.LogInfoDto;
import com.example.audit.core.entity.Log;
import com.example.audit.core.entity.User;
import com.example.audit.repository.LogsRepository;
import com.example.audit.service.LogsService;
import com.example.audit.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LogsServiceImpl implements LogsService {
    private final LogsRepository repository;
    private final UsersService usersService;

    @Override
    public void createLog(final LogDto logDto) {
        final User subject = usersService.saveNewUserIfNotExist(logDto.getSubjectName());
        User object;

        if (logDto.getObjectName() != null) {
            object = usersService.saveNewUserIfNotExist(logDto.getObjectName());
        } else {
            object = null;
        }

        repository.save(new Log(
                logDto.getAction(),
                subject,
                object,
                logDto.getActionTime()
        ));
    }

    @Override
    public List<LogInfoDto> getLogsByUserId(final Long id) {
        return repository.getLogsBySubjectUserOrderByActionTimeDesc(usersService.getById(id)).stream()
                .map(log -> {
                    String objectName;
                    objectName = log.getObjectUser() != null ? log.getObjectUser().getUsername() : null;
                    return new LogInfoDto(
                            log.getSubjectUser().getUsername(),
                            log.getAction(),
                            objectName,
                            log.getActionTime().getMonth().name().toLowerCase(),
                            log.getActionTime().getDayOfMonth(),
                            log.getActionTime().getHour(),
                            log.getActionTime().getMinute()
                    );
                }).toList();
    }
}

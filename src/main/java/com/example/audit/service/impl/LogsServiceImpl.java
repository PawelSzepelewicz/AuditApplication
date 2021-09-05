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
    public void createLog(LogDto logDto) {
        User subject = usersService.saveNewUserIfNotExist(logDto.getSubjectName());
        User object;

        if (logDto.getObjectName() != null) {
            object = usersService.saveNewUserIfNotExist(logDto.getObjectName());
        } else object = null;

        Log log = new Log();
        log.setAction(logDto.getAction());
        log.setSubjectUser(subject);
        log.setObjectUser(object);
        log.setActionTime(logDto.getActionTime());
        repository.save(log);
    }

    @Override
    public List<LogInfoDto> getLogsByUserId(Long id) {
        return repository.getLogsBySubjectUser(usersService.getById(id)).stream()
                .map(log -> {
                    String objectName;

                    if (log.getObjectUser() != null) {
                        objectName = log.getObjectUser().getUsername();
                    } else objectName = null;

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

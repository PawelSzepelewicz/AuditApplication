package com.example.audit.service;

import com.example.audit.core.dto.LogDto;
import com.example.audit.core.dto.LogInfoDto;

import java.util.List;

public interface LogsService {
    void createLog(LogDto logDto);

    List<LogInfoDto> getLogsByUserId(Long id);
}

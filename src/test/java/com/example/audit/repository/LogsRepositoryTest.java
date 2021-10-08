package com.example.audit.repository;

import com.example.audit.core.entity.Log;
import com.example.audit.core.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogsRepositoryTest extends RepositoryTest {
    @Autowired
    private LogsRepository repository;

    @Test
    void getLogsBySubjectUserOrderByActionTimeDesc() {
        List<LocalDateTime> timeList = new ArrayList<>();
        List<Log> logsList = repository.getUsersLogs(1L);
        logsList.forEach(log ->
              timeList.add(log.getActionTime()));
        assertEquals(timeList, timeList.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()));
    }
}

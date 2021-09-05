package com.example.audit.repository;

import com.example.audit.core.entity.Log;
import com.example.audit.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogsRepository extends JpaRepository<Log, Long> {
    List<Log> getLogsBySubjectUser(User user);
}

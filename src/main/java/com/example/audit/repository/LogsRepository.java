package com.example.audit.repository;

import com.example.audit.core.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogsRepository extends JpaRepository<Log, Long> {
    @Query(value = "select * from logs where subject_user = :id order by action_time desc", nativeQuery = true)
    List<Log> getUsersLogs(Long id);
}

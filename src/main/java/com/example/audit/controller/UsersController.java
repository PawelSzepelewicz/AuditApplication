package com.example.audit.controller;

import com.example.audit.core.dto.LogInfoDto;
import com.example.audit.core.entity.User;
import com.example.audit.service.LogsService;
import com.example.audit.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final LogsService logsService;

    @GetMapping()
    public ResponseEntity<List<User>> getUsersList() {
        return ResponseEntity.ok(usersService.getListOfUsers());
    }

    @GetMapping("/logs")
    public ResponseEntity<List<LogInfoDto>> getUsersLogs(@RequestParam("id") final Long id) {
        return ResponseEntity.ok(logsService.getLogsByUserId(id));
    }
}

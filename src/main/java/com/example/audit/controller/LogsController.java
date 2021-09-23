package com.example.audit.controller;

import com.example.audit.core.dto.LogInfoDto;
import com.example.audit.service.LogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogsController {
    private final LogsService logsService;

    @GetMapping
    public String getUsersLogs(@RequestParam("id") final Long id, final Model model) {
        List<LogInfoDto> logsList = logsService.getLogsByUserId(id);
        model.addAttribute("logsList", logsList);
        return "logs";
    }
}

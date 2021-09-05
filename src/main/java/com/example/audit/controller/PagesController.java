package com.example.audit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    @Value("${server.host}")
    private String host;

    @GetMapping("/list")
    public String list(final Model model) {
        model.addAttribute("host", host);
        return "list";
    }

    @GetMapping("/logs")
    public String logs(final Model model) {
        model.addAttribute("host", host);
        return "logs";
    }
}

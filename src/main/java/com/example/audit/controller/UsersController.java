package com.example.audit.controller;

import com.example.audit.core.entity.User;
import com.example.audit.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public String getUserList(final Model model) {
        List<User> userList = usersService.getListOfUsers();
        model.addAttribute("userList", userList);
        return "list";
    }
}

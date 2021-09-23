package com.example.audit.controller;

import com.example.audit.core.dto.UserDto;
import com.example.audit.service.UsersService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
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
    private final MapperFacade mapper;

    @GetMapping
    public String getUserList(final Model model) {
        List<UserDto> userList = mapper.mapAsList(usersService.getListOfUsers(), UserDto.class);
        model.addAttribute("userList", userList);
        return "list";
    }
}

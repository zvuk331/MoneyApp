package ru.java.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.learn.entity.User;
import ru.java.learn.repository.UserRepository;

@Controller
@RequestMapping("/{id}")
public class UserController {

    @Autowired
    private UserRepository userRepository;


}

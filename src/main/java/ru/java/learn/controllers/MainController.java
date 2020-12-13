package ru.java.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.java.learn.entity.User;
import ru.java.learn.repository.UserRepository;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/main")
    public String mainPage(@RequestParam(required = false) String username,Model model){
        List<User> users = userRepository.findAllBy();
        model.addAttribute("users",users);
        return "main";
    }
    @GetMapping("/home")
    public String homePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        model.addAttribute("user", user);
        return "home";
    }







}

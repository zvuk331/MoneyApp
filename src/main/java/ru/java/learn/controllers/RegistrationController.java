package ru.java.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import ru.java.learn.entity.Role;
import ru.java.learn.entity.User;
import ru.java.learn.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("userExist","");
        return "registration";
    }

    @PostMapping("/registration")
    public @ResponseBody
    RedirectView addNewUser(@RequestParam(name = "username") String username,
                            @RequestParam(name = "password") String password,
                            User user,
                            Model model){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB !=null){
            model.addAttribute("userExist", "Пользователь с таким логином уже существует!");
            return new RedirectView("/registration");
        }
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return new RedirectView("/login");
    }
}

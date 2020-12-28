package ru.java.learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import ru.java.learn.entity.Role;
import ru.java.learn.entity.User;
import ru.java.learn.repository.UserRepository;
import ru.java.learn.service.SecurityUser;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationPage( @RequestParam(value = "message", required = false) String message,
                                    Model model){
        User user = new User();
        model.addAttribute("message", message);
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@Valid User user,
                            String message,
                            BindingResult result,
                            Model model){

        User userFromDB = userRepository.findByEmail(user.getEmail());
        if (userFromDB != null){
            message = "Пользователь с такой почтой уже существует!";
            model.addAttribute("message", message);
            return ("/registration");
        }
        if (result.hasFieldErrors()) {
            return ("redirect:/registration");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);


        return ("/login");
    }
}

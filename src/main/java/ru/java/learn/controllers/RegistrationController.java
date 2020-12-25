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
        model.addAttribute("message", message);
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

        userRepository.save(user);

        if (result.hasErrors()) {
            
            return ("registration");
        }

        return ("/login");
    }
}

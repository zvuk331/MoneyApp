package ru.java.learn.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import ru.java.learn.entity.User;
import ru.java.learn.repository.UserRepository;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }


    @GetMapping
    public String indexPage(){
        return "index";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }
    @PostMapping(path = "/add")
    public @ResponseBody
    RedirectView addNewUSer(@RequestParam String username,@RequestParam String email, @RequestParam String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return new RedirectView("index");
    }

   /* @GetMapping("/login")
    public String loginPage(){
        return "login";
    }*/
}

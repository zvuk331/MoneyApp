package ru.java.learn.controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }
}

package moneyhelper.controller;

import moneyhelper.entity.User;
import moneyhelper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

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
                             BindingResult result,
                             @RequestParam(value = "password2", required = true) @Valid String password2,
                             Model model){
        User userFromDB = userService.findUserByEmail(user.getEmail());
        if (userFromDB != null){
            model.addAttribute("message", "Пользователь с такой почтой уже существует!");
            return ("registration");
        }
        if (!user.getPassword().equals(password2)){
            model.addAttribute("message", "Пароли не совпадают!");
            return "registration";
        }

        if (result.hasErrors()) {
            return ("registration");
        }

        userService.saveNewUser(user);
        return ("redirect:/login");
    }
}

package moneyhelper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(ModelMap model, HttpServletRequest request){
        if (request.getParameterMap().containsKey("error")){
            model.addAttribute("error", true);
        }
        return "login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login?logout";
    }
}

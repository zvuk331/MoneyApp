package moneyhelper.controller;

import moneyhelper.entity.User;
import moneyhelper.entity.UserCosts;
import moneyhelper.entity.UserDetails;
import moneyhelper.entity.UserIncome;
import moneyhelper.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String homePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("user", user);

        List<UserCosts> userCostsList = user.getFinance().getCosts();
        model.addAttribute("userCostsList", userCostsList);

        List<UserIncome> userIncomesList = user.getFinance().getIncomes();
        model.addAttribute("userIncomesList", userIncomesList);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfilePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDetails userDetails = user.getDetails();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/profile/edit")
    public String editUserDetails(UserDetails userDetails){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        userDetails.setUser(user);
        user.setDetails(userDetails);

        userService.updateDetails(user);
        return "redirect:/profile";
    }

}

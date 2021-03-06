package moneyhelper.controller;

import moneyhelper.entity.*;
import moneyhelper.service.FinanceService;
import moneyhelper.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FinanceController {

    private final UserService userService;
    private final FinanceService financeService;

    public FinanceController(UserService userService, FinanceService financeService) {
        this.userService = userService;
        this.financeService = financeService;
    }

    @GetMapping("/finance")
    public String mainPage(Model model) {
        User user = getAuthentificationUser();
        model.addAttribute("user", user);

        UserIncome income = new UserIncome();
        UserCosts costs = new UserCosts();

        model.addAttribute("costs", costs);
        model.addAttribute("income", income);

        List<TypesOfIncome> typesOfIncomes = financeService.findAllIncomes();
        model.addAttribute("typesOfIncomes", typesOfIncomes);

        List<TypesOfCosts> typesOfCosts = financeService.findAllCosts();
        model.addAttribute("typesOfCosts", typesOfCosts);

        List<UserCosts> userCostsList = user.getFinance().getCosts();
        model.addAttribute("userCostsList", userCostsList);

        List<UserIncome> userIncomesList = user.getFinance().getIncomes();
        model.addAttribute("userIncomesList", userIncomesList);

        return "finance";
    }

    @PostMapping("/finance/addcosts")
    public String addCosts(@RequestParam(value = "value") double value,
                           @RequestParam(value = "type") String type,
                           Model model){
        User user = getAuthentificationUser();
        financeService.addCosts(user, value, type);

        return "redirect:/finance";
    }

    @PostMapping("/finance/addincome")
    public String addIncome(@RequestParam(value = "value") double value,
                           @RequestParam(value = "type") String type,
                           Model model){
        User user = getAuthentificationUser();
        financeService.addIncome(user, value, type);

        return "redirect:/finance";
    }

    protected User getAuthentificationUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }

}

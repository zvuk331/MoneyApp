package moneyhelper.controller;

import moneyhelper.entity.User;
import moneyhelper.entity.UserDetails;
import moneyhelper.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String homePage(Model model) {
        User user = getAuthentificationUser();
        UserDetails details = user.getDetails();
        model.addAttribute("userDetails", details);
        model.addAttribute("user", user);

        String filename = details.getFileName();
        model.addAttribute("filename", filename);

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfilePage(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
        User user = getAuthentificationUser();
        UserDetails userDetails = user.getDetails();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/profile/edit")
    public String editUserDetails(@RequestParam("file") MultipartFile file,
                                  @RequestParam(value = "password", required = false) @Valid String password,
//                                  @RequestParam(value = "password",required = false) @Valid String password2,
                                  UserDetails userDetails,
                                  Model model) {
        User user = getAuthentificationUser();

        if (!file.isEmpty()) {
            userService.updatePhoto(file, user);
        }
        if (!password.isEmpty()) {
            userService.updatePassword(user, password);
        }
        userDetails.setUser(user);
        user.setDetails(userDetails);
        userService.updateDetails(user);
        return"redirect:/profile";
    }
    protected User getAuthentificationUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        return user;
    }
}

package kz.ermek.DutyGraphicProject.controllers;

import kz.ermek.DutyGraphicProject.models.Users;
import kz.ermek.DutyGraphicProject.services.RegistrationService;
import kz.ermek.DutyGraphicProject.util.UsersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final UsersValidator usersValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UsersValidator usersValidator) {
        this.registrationService = registrationService;
        this.usersValidator = usersValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("users")Users users){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registrationPage(@ModelAttribute("users")@Valid Users users,
                                   BindingResult bindingResult){

        usersValidator.validate(users, bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        registrationService.register(users);

        return "redirect:/auth/login";
    }
}

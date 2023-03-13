package kz.ermek.DutyGraphicProject.util;

import kz.ermek.DutyGraphicProject.models.Users;
import kz.ermek.DutyGraphicProject.services.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsersValidator implements Validator {
    private final UsersDetailsService usersDetailsService;

    @Autowired
    public UsersValidator(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users users = (Users) target;

        try {
            usersDetailsService.loadUserByUsername(users.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "",
                "User with this name already exist");
    }
}

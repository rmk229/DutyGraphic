package kz.ermek.DutyGraphicProject.controllers;

import kz.ermek.DutyGraphicProject.repositories.UsersRepository;
import kz.ermek.DutyGraphicProject.security.UsersDetails;
import kz.ermek.DutyGraphicProject.services.AdminService;
import kz.ermek.DutyGraphicProject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
    private final AdminService adminService;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    @Autowired
    public HelloController(AdminService adminService, UsersRepository usersRepository, UsersService usersService) {
        this.adminService = adminService;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        System.out.println(usersDetails.getUsers());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStaff();
        return "admin";
    }


    @GetMapping("/users")
    public String index(Model model) {
        adminService.doAdminStaff();
        model.addAttribute("admin", usersService.findAll());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        adminService.doAdminStaff();
        model.addAttribute("admin", usersService.findOne(id));
        return "showUserData";
    }
}

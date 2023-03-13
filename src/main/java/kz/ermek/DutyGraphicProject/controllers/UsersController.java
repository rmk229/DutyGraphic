package kz.ermek.DutyGraphicProject.controllers;

import kz.ermek.DutyGraphicProject.repositories.UsersRepository;
import kz.ermek.DutyGraphicProject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/users")
//public class UsersController {
//    private final UsersRepository usersRepository;
//    private final UsersService usersService;
//
//    @Autowired
//    public UsersController(UsersRepository usersRepository, UsersService usersService) {
//        this.usersRepository = usersRepository;
//        this.usersService = usersService;
//    }

//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("users", usersService.findAll());
//        return "users/index";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("users", usersService.findOne(id));
//        return "users/show";
//    }
//}

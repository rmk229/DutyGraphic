package kz.ermek.DutyGraphicProject.controllers;

import kz.ermek.DutyGraphicProject.models.Duty;
import kz.ermek.DutyGraphicProject.repositories.DutyRepository;
import kz.ermek.DutyGraphicProject.services.DutyService;
import kz.ermek.DutyGraphicProject.util.DutyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/duty")
public class DutyController {
    private final DutyService dutyService;
    private final DutyValidator dutyValidator;
    private final DutyRepository dutyRepository;


    @Autowired
    public DutyController(DutyService dutyService, DutyValidator dutyValitator, DutyRepository dutyRepository) {
        this.dutyService = dutyService;
        this.dutyValidator = dutyValitator;
        this.dutyRepository = dutyRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("duties", dutyService.findAll());
        return "duty/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("duty", dutyService.findOne(id));
        model.addAttribute("orderlies", dutyService.getOrderliesByDutyId(id));

        return "duty/show";
    }

    @GetMapping("/new")
    public String newDuty(@ModelAttribute("duty")Duty duty) {
        return "duty/new";
    }

    @PostMapping
    public String create(@ModelAttribute("duty") @Valid Duty duty,
                         BindingResult bindingResult) {
        dutyValidator.validate(duty, bindingResult);

        if (bindingResult.hasErrors()) {
            return "duty/new";
        }
        dutyService.save(duty);
        return "redirect:/duty";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("duty", dutyService.findOne(id));
        return "duty/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("duty") Duty duty,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "duty/edit";
        }

        dutyService.update(id, duty);
        return "redirect:/duty";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dutyService.delete(id);
        return "redirect:/duty";
    }
}

package kz.ermek.DutyGraphicProject.controllers;

import kz.ermek.DutyGraphicProject.models.Duty;
import kz.ermek.DutyGraphicProject.models.Orderly;
import kz.ermek.DutyGraphicProject.services.DutyService;
import kz.ermek.DutyGraphicProject.services.OrderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/orderly")
public class OrderlyController {
    private final OrderlyService orderlyService;
    private final DutyService dutyService;

    @Autowired
    public OrderlyController(OrderlyService orderlyService, DutyService dutyService) {
        this.orderlyService = orderlyService;
        this.dutyService = dutyService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("orderlies", orderlyService.findAll());
        return "orderly/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("duty") Duty duty) {

        model.addAttribute("orderly", orderlyService.findOne(id));

        Duty himOrderly = orderlyService.getOrdDuty(id);

        if (himOrderly != null) {
            model.addAttribute("duty", himOrderly);
        } else {
            model.addAttribute("duties", dutyService.findAll());
        }

        return "orderly/show";
    }

    @GetMapping("/new")
    public String newOrderly(@ModelAttribute("orderly") Orderly orderly) {
        return "orderly/new";
    }

    @PostMapping
    public String create(@ModelAttribute("orderly") @Valid Orderly orderly,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orderly/new";
        }

        orderlyService.save(orderly);

        return "redirect:/orderly";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("orderly", orderlyService.findOne(id));
        return "orderly/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("orderly") @Valid Orderly orderly,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "orderly/edit";
        }
        orderlyService.update(id, orderly);
        return "redirect:/orderly";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        orderlyService.delete(id);
        return "redirect:/orderly";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("duty") Duty selectedDuty) {
        orderlyService.assign(id, selectedDuty);
        return "redirect:/orderly/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        orderlyService.release(id);
        return "redirect:/orderly/" + id;
    }

    @GetMapping("/search")
    public String searchOrderly() {
        return "orderly/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("query") String query) {
        model.addAttribute("orderlies", orderlyService.searchByName(query));
        return "orderly/search";
    }
}

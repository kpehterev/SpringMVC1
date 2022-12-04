package control.controllers;

import control.services.Services;
import control.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class ControllerLibrary {
    private final Services services;

    @Autowired
    public ControllerLibrary(Services services) {
        this.services = services;
    }

    @GetMapping()
    private String showList(Model model){
        model.addAttribute("people", services.findAll());
        return "/people/show";
    }

    @GetMapping("/{id}")
    private String indexPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", services.index(id));

        return "/people/index";
    }

    @GetMapping("/{id}/edit")
    private String updatePerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", services.index(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}/edit")
    private String personPatch(@ModelAttribute("person")  Person person, @PathVariable("id") int id){

        services.update(id,person);
        return "/people/edit";
    }
    @GetMapping("/new")
    private String createNewPerson(@ModelAttribute("person") Person person){
        return "/people/new";
    }

    @PostMapping("/new")
    private String savePerson(@ModelAttribute("person") Person person) {
        services.save(person);
        return "redirect: /people";
    }

    @DeleteMapping("/people/{id}")
    private String deletePerson(@PathVariable("id") int id){
        services.delete(id);
        return "redirect: /people";
    }
}

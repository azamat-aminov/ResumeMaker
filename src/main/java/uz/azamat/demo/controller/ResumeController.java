package uz.azamat.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uz.azamat.demo.model.Person;
import uz.azamat.demo.service.PersonService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ResumeController {

    @Resource
    PersonService personService;

    @GetMapping
    public String getPage() {
        return "login";
    }

    @PostMapping("/person")
    public String getForms() {
        return "person";
    }

    @PostMapping("/savePerson")
    public String savePersons(Person person) {
        System.out.println(person.getFullName());
        personService.save(person);
        return "person";
    }

    @GetMapping("/getAllData")
    public String getAllData(Model model) {
        List<Person> allData = personService.getAllData();
        model.addAttribute("data", allData);
        return "resumes";
    }
}

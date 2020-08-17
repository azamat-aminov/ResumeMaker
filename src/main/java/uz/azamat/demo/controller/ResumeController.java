package uz.azamat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uz.azamat.demo.model.EducationDegree;
import uz.azamat.demo.model.Person;
import uz.azamat.demo.service.EducationDegreeService;
import uz.azamat.demo.service.PersonService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ResumeController {

    @Resource
    PersonService personService;
    @Autowired
    EducationDegreeService educationDegreeService;

    @GetMapping()
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/createPerson")
    public String createPerson(Person person) {
        return "person";
    }

    @PostMapping("/form")
    public String callFormPage(Person person) {
        return "person";
    }

    @PostMapping("/savePerson")
    public String savePersons(Person person, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "person";
        }
        personService.save(person);
        List<Person> allData = personService.getAllData();
        model.addAttribute("data", allData);
        return "resumes";
    }

    @GetMapping("/edit/{id}")
    public String findById(@PathVariable int id, Model model) {
        Person person = personService.findById(id);
        model.addAttribute("person", person);
        return "updateUser";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable int id, @Valid Person person,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "updateUser";
        }
        personService.updatePerson(person, id);
        model.addAttribute("data", personService.getAllData());
        return "resumes";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id, Model model) {
        personService.deletePerson(id);
        model.addAttribute("data", personService.getAllData());
        return "resumes";
    }
    @GetMapping("/data/by/{id}")
    public String getOnePerson(@PathVariable int id, Model model){
         model.addAttribute("onePerson", personService.findById(id));
        return "onePersonInfo";
    }

    @GetMapping("/getAllData")
    public String getAllData(Model model) {
        List<Person> allData = personService.getAllData();
        model.addAttribute("data", allData);
        return "resumes";
    }
}

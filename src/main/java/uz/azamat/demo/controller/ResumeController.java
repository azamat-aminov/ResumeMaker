package uz.azamat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uz.azamat.demo.model.EducationDegree;
import uz.azamat.demo.model.Person;
import uz.azamat.demo.service.EducationDegreeService;
import uz.azamat.demo.service.PersonService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ResumeController {

    @Resource
    PersonService personService;
    @Autowired
    EducationDegreeService educationDegreeService;

    @GetMapping
    public String getPage() {
        return "login";
    }

    @PostMapping("/person")
    public String getForms() {
        return "person";
    }

    @PostMapping("/savePerson")
    public String savePersons(Person person, EducationDegree educationDegree) {
        System.out.println(person.getFullName());
        educationDegreeService.save(educationDegree);
        personService.save(person);
        return "person";
    }

    @GetMapping("/getAllData")
    public String getAllData(Model model) {
        List<Person> allData = personService.getAllData();
        List<EducationDegree> allInfoAboutEdu = educationDegreeService.getAllInfoAboutEdu();
        model.addAttribute("data", allData);
        model.addAttribute("eduData", allInfoAboutEdu);
        return "resumes";
    }
}

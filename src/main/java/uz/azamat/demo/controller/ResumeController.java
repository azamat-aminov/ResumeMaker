package uz.azamat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uz.azamat.demo.dao.PersonImpl;
import uz.azamat.demo.model.EducationDegree;
import uz.azamat.demo.model.Person;
import uz.azamat.demo.service.EducationDegreeService;
import uz.azamat.demo.service.PersonService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

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
    public String createPerson(Person person, EducationDegree edu) {
        return "person";
    }

    @PostMapping("/form")
    public String callFormPage(Person person, EducationDegree edu) {
        return "person";
    }

    @PostMapping("/savePerson")
    public String savePersons(Person person, Model model, BindingResult result, HttpServletRequest req) {
        if (result.hasErrors()) {
            return "person";
        }
        List<EducationDegree> educationDegrees = new ArrayList<>();
        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] universityNames = parameterMap.get("universityName");
        String[] gradYear = parameterMap.get("graduatedYear");
        String[] degrees = parameterMap.get("degree");
        System.out.println(Arrays.toString(universityNames));
        System.out.println(Arrays.toString(gradYear));
        System.out.println(Arrays.toString(degrees));
        for (int i = 0; i < universityNames.length; i++) {
            EducationDegree edu = new EducationDegree();
            String universityName = universityNames[i];
            String graduatedYear = gradYear[i];
            String degree = degrees[i];
            Date date = Date.valueOf(graduatedYear);
            edu.setUniversityName(universityName);
            edu.setGraduatedYear(date);
            edu.setDegree(degree);
            educationDegrees.add(edu);
        }
        personService.save(person);
        educationDegreeService.save(educationDegrees, PersonImpl.KEY);
        List<Person> allData = personService.getAllData();
        model.addAttribute("data", allData);
        return "resumes";
    }

    @GetMapping("/edit/{id}")
    public String findById(@PathVariable int id, Model model) {
        Person person = personService.findById(id);
        List<EducationDegree> educationDegrees = educationDegreeService.eduFindById(id);
        model.addAttribute("person", person);
        model.addAttribute("universities", educationDegrees);
        return "updateUser";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable int id, Person person, HttpServletRequest request,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "updateUser";
        }
        List<EducationDegree> educationDegrees = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] universityNames = parameterMap.get("universityName");
        String[] gradYear = parameterMap.get("graduatedYear");
        String[] degrees = parameterMap.get("degree");
        String[] ids = parameterMap.get("degreeId");

        System.out.println(Arrays.toString(universityNames));
        System.out.println(Arrays.toString(gradYear));
        System.out.println(Arrays.toString(degrees));
        System.out.println(Arrays.toString(ids));

        for (int i = 0; i < universityNames.length; i++) {
            EducationDegree edu = new EducationDegree();
            String universityName = universityNames[i];
            String graduatedYear = gradYear[i];
            String degree = degrees[i];
            String degreeId = ids[i];
            Date date = Date.valueOf(graduatedYear);
            edu.setUniversityName(universityName);
            edu.setGraduatedYear(date);
            edu.setDegree(degree);
            edu.setUniversityId(Integer.parseInt(degreeId));
            educationDegrees.add(edu);
        }
        System.out.println("to update list: " + educationDegrees);
        personService.updatePerson(person, id);
        educationDegreeService.updateDegree(educationDegrees);
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
    public String getOnePerson(@PathVariable int id, Model model) {
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

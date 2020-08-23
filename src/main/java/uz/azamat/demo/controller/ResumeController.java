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
import uz.azamat.demo.model.WorkPlace;
import uz.azamat.demo.service.EducationDegreeService;
import uz.azamat.demo.service.PersonService;
import uz.azamat.demo.service.WorkPlaceService;

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
    @Resource
    WorkPlaceService workPlaceService;

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
        List<WorkPlace> workPlaces = new ArrayList<>();
        Map<String, String[]> parameterMap = req.getParameterMap();

        String[] universityNames = parameterMap.get("universityName");
        String[] gradYear = parameterMap.get("graduatedYear");
        String[] degrees = parameterMap.get("degree");
        String[] workPlacesArray = parameterMap.get("workPlaceName");
        System.out.println(Arrays.toString(workPlacesArray));

        for (int i = 0; i < universityNames.length; i++) {
            EducationDegree edu = new EducationDegree();
            String universityName = universityNames[i];
            String graduatedYear = gradYear[i];
            String degree = degrees[i];
            if (!graduatedYear.isEmpty()) {
                Date date = Date.valueOf(graduatedYear);
                edu.setGraduatedYear(date);
            }
            edu.setUniversityName(universityName);
            edu.setDegree(degree);
            educationDegrees.add(edu);
        }
        for (String s : workPlacesArray) {
            WorkPlace workPlace = new WorkPlace();
            workPlace.setWorkPlaceName(s);
            workPlaces.add(workPlace);
        }
        personService.save(person);
        educationDegreeService.save(educationDegrees, PersonImpl.KEY);
        workPlaceService.save(workPlaces, PersonImpl.KEY);
        List<Person> allData = personService.getAllData();
        model.addAttribute("data", allData);
        return "resumes";
    }

    @GetMapping("/edit/{id}")
    public String findById(@PathVariable int id, Model model) {
        Person person = personService.findById(id);
        List<EducationDegree> educationDegrees = educationDegreeService.eduFindById(id);
        List<WorkPlace> workPlaces = workPlaceService.getWorkPlacesById(id);
        model.addAttribute("person", person);
        model.addAttribute("universities", educationDegrees);
        model.addAttribute("workPlaces", workPlaces);
        return "updateUser";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable int id, Person person, HttpServletRequest request,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "updateUser";
        }
        Map<String, String[]> parameterMap = request.getParameterMap();


        deleteEdu(id, parameterMap);
        deleteWork(id, parameterMap);
        List<WorkPlace> mainWorkPlacesList = updateOrSaveWorkplaces(id, parameterMap);
        List<EducationDegree> educationDegrees = updateOrSaveEdu(id, parameterMap);


        personService.updatePerson(person, id);
        educationDegreeService.updateDegree(educationDegrees);
        workPlaceService.updateWorkplace(mainWorkPlacesList);
        model.addAttribute("data", personService.getAllData());
        return "resumes";
    }

    private void deleteWork(int id, Map<String, String[]> parameterMap) {
        String[] workplacesIds = parameterMap.get("workPlaceId");
        List<Integer> workPlaceListIds = new ArrayList<>();
        List<Integer> getAllWorkplaceIds = new ArrayList<>();
        List<WorkPlace> allInfoAboutWorkplace = workPlaceService.getWorkPlacesById(id);
        for (WorkPlace place : allInfoAboutWorkplace) {
            getAllWorkplaceIds.add(place.getWorkPlaceId());
        }
        for (String s : workplacesIds) {
            if (!s.equals("")) {
                workPlaceListIds.add(Integer.valueOf(s));
            }
        }
        if (workPlaceListIds.size() < getAllWorkplaceIds.size()) {
            List<Integer> deletedWorkplaces = new ArrayList<>(getAllWorkplaceIds);
            deletedWorkplaces.removeAll(workPlaceListIds);
            for (int del : deletedWorkplaces) {
                workPlaceService.deleteByWorkplaceId(del);
            }
        }
    }

    private void deleteEdu(int id, Map<String, String[]> parameterMap) {
        String[] ids = parameterMap.get("degreeId");
        System.out.println(Arrays.toString(ids));
        List<Integer> listIds = new ArrayList<>();
        List<Integer> getAllIds = new ArrayList<>();

        List<EducationDegree> allInfoAboutEdu = educationDegreeService.eduFindById(id);
        for (EducationDegree deg : allInfoAboutEdu) {
            getAllIds.add(deg.getUniversityId());
        }
        for (String s : ids) {
            if (!s.equals("")) {
                listIds.add(Integer.valueOf(s));
            }
        }
        if (listIds.size() < getAllIds.size()) {
            List<Integer> deletedId = new ArrayList<>(getAllIds);
            deletedId.removeAll(listIds);
            for (int del : deletedId) {
                educationDegreeService.deleteByUniversityId(del);
            }
        }
    }

    private List<EducationDegree> updateOrSaveEdu(int id, Map<String, String[]> parameterMap) {
        String[] universityNames = parameterMap.get("universityName");
        String[] gradYear = parameterMap.get("graduatedYear");
        String[] degrees = parameterMap.get("degree");
        String[] ids = parameterMap.get("degreeId");

        List<EducationDegree> educationDegrees = new ArrayList<>();
        for (int i = 0; i < universityNames.length; i++) {
            EducationDegree edu = new EducationDegree();
            String universityName = universityNames[i];
            String graduatedYear = gradYear[i];

            String degree = degrees[i];
            String degreeId = ids[i];


            if (ids[i].equals("") || ids[i].isEmpty()) {
                List<EducationDegree> newEduDegrees = new ArrayList<>();
                EducationDegree newEdu = new EducationDegree();
                String newUniversityName = universityNames[i];
                String newGraduatedYear = gradYear[i];
                String newDegree = degrees[i];
                newEdu.setUniversityName(newUniversityName);
                Date date2 = Date.valueOf(newGraduatedYear);
                newEdu.setGraduatedYear(date2);
                newEdu.setDegree(newDegree);
                newEduDegrees.add(newEdu);
                educationDegreeService.save(newEduDegrees, id);
            } else {
                if (!graduatedYear.isEmpty()) {
                    Date date = Date.valueOf(graduatedYear);
                    edu.setGraduatedYear(date);
                }
                edu.setUniversityName(universityName);
                edu.setDegree(degree);
                edu.setUniversityId(Integer.parseInt(degreeId));
                educationDegrees.add(edu);
            }
        }
        return educationDegrees;
    }

    private List<WorkPlace> updateOrSaveWorkplaces(int id, Map<String, String[]> parameterMap) {
        String[] workplaces = parameterMap.get("workPlaceName");
        String[] workplacesIds = parameterMap.get("workPlaceId");

        List<WorkPlace> mainWorkPlacesList = new ArrayList<>();
        for (int k = 0; k < workplaces.length; k++) {
            WorkPlace workPlace = new WorkPlace();
            String workplacesNames = workplaces[k];
            String workplacesId = workplacesIds[k];
            if (workplacesIds[k].equals("") || workplacesIds[k].isEmpty()) {
                List<WorkPlace> workPlaceList = new ArrayList<>();
                WorkPlace newWorkPlace = new WorkPlace();
                String newWorkplaceName = workplaces[k];
                newWorkPlace.setWorkPlaceName(newWorkplaceName);
                workPlaceList.add(newWorkPlace);
                workPlaceService.save(workPlaceList, id);
            } else {
                workPlace.setWorkPlaceName(workplacesNames);
                workPlace.setWorkPlaceId(Integer.parseInt(workplacesId));
                mainWorkPlacesList.add(workPlace);
            }
        }
        return mainWorkPlacesList;
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id, Model model) {
        educationDegreeService.deleteById(id);
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

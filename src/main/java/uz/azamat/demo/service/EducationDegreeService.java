package uz.azamat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.azamat.demo.dao.EducationDegreeDao;
import uz.azamat.demo.model.EducationDegree;

import java.util.List;

@Service
public class EducationDegreeService {
    @Autowired
    EducationDegreeDao educationDegreeDao;


    public void save(EducationDegree educationDegree){
        educationDegreeDao.save(educationDegree);
    }

    public List<EducationDegree> getAllInfoAboutEdu(){
       return educationDegreeDao.getAllData();
    }
}

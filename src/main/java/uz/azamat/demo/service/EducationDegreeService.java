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


    public void save(List<EducationDegree> educationDegrees, int id) {
        educationDegreeDao.save(educationDegrees, id);
    }

    public List<EducationDegree> getAllInfoAboutEdu() {
        return educationDegreeDao.getAllData();
    }

    public List<EducationDegree> eduFindById(int id) {
        return educationDegreeDao.eduFindById(id);
    }

    public void updateDegree(List<EducationDegree> degrees) {
        educationDegreeDao.updateDegree(degrees);
    }
    public void deleteByUniversityId(int id){
        educationDegreeDao.deleteByUniversityId(id);
    }

}

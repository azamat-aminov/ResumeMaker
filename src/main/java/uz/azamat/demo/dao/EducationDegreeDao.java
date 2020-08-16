package uz.azamat.demo.dao;

import uz.azamat.demo.model.EducationDegree;
import uz.azamat.demo.model.Person;

import java.util.List;

public interface EducationDegreeDao {
    void save(EducationDegree educationDegree);

    List<EducationDegree> getAllData();
}

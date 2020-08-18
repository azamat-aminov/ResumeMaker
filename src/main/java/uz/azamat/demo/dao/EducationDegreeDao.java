package uz.azamat.demo.dao;

import uz.azamat.demo.model.EducationDegree;

import java.util.List;

public interface EducationDegreeDao {
    void save(List<EducationDegree> educationDegrees);

    List<EducationDegree> getAllData();
}

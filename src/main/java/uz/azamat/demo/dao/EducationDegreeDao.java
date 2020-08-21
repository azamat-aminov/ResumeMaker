package uz.azamat.demo.dao;

import uz.azamat.demo.model.EducationDegree;

import java.util.List;

public interface EducationDegreeDao {
    void save(List<EducationDegree> educationDegrees, int i);

    List<EducationDegree> getAllData();

    List<EducationDegree> eduFindById(int id);

     void updateDegree(List<EducationDegree> degrees);

     void deleteByUniversityId(int universityId);
     void deleteById(int id);
}

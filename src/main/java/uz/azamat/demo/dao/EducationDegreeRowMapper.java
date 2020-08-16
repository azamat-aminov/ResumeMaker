package uz.azamat.demo.dao;

import org.springframework.jdbc.core.RowMapper;
import uz.azamat.demo.model.EducationDegree;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EducationDegreeRowMapper implements RowMapper<EducationDegree> {
    @Override
    public EducationDegree mapRow(ResultSet resultSet, int i) throws SQLException {
        EducationDegree degree = new EducationDegree();
        degree.setUniversityName(resultSet.getString("name"));
        degree.setGraduatedYear(resultSet.getDate("date"));
        degree.setDegree(resultSet.getString("degree"));

        return degree;
    }
}

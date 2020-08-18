package uz.azamat.demo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.azamat.demo.model.EducationDegree;

import java.util.List;

@Repository
public class EducationDegreeImpl implements EducationDegreeDao {
    JdbcTemplate jdbcTemplate;

    public EducationDegreeImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(List<EducationDegree> educationDegrees) {
        String query = "insert into university(name ,graduated_year,degree)" +
                "values(?,?,?)";
        for (EducationDegree degree : educationDegrees) {
            jdbcTemplate.update(query, degree.getUniversityName(),
                    degree.getGraduatedYear(),
                    degree.getDegree());
        }
    }

    @Override
    public List<EducationDegree> getAllData() {
        String query = "select * from university";
        return jdbcTemplate.query(query, new EducationDegreeRowMapper());
    }
}

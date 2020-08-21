package uz.azamat.demo.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.azamat.demo.model.EducationDegree;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EducationDegreeImpl implements EducationDegreeDao {
    JdbcTemplate jdbcTemplate;

    public EducationDegreeImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(List<EducationDegree> educationDegrees, int id) {
        String query = "insert into universities(name ,graduated_year,degree, id)" +
                "values(?,?,?,?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                EducationDegree edu = educationDegrees.get(i);
                ps.setString(1, edu.getUniversityName());
                ps.setDate(2, edu.getGraduatedYear());
                ps.setString(3, edu.getDegree());
                ps.setInt(4, id);
            }

            @Override
            public int getBatchSize() {
                return educationDegrees.size();
            }
        });
    }

    @Override
    public List<EducationDegree> getAllData() {
        String query = "select * from universities";
        return jdbcTemplate.query(query, new EducationDegreeRowMapper());
    }

    @Override
    public List<EducationDegree> eduFindById(int id) {
        String query = "select * from universities where id=" + id;
        return jdbcTemplate.query(query, new EducationDegreeRowMapper());
    }

    @Override
    public void updateDegree(List<EducationDegree> degrees) {
        String query = "update universities set name=?, graduated_year=?, degree=? where univer_id=?";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, degrees.get(i).getUniversityName());
                ps.setDate(2, degrees.get(i).getGraduatedYear());
                ps.setString(3, degrees.get(i).getDegree());
                ps.setInt(4, degrees.get(i).getUniversityId());
            }

            @Override
            public int getBatchSize() {
                return degrees.size();
            }
        });
    }

    @Override
    public void deleteByUniversityId(int universityId) {
        String query = "delete from universities where univer_id =?";
        jdbcTemplate.update(query, universityId);
    }

    @Override
    public void deleteById(int id) {
          String query = "delete from universities where id = ?";
          jdbcTemplate.update(query, id);
    }
}

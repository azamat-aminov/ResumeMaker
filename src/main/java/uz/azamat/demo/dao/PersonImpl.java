package uz.azamat.demo.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.azamat.demo.model.Person;

import java.util.List;

@Repository
public class PersonImpl implements PersonDao {
    JdbcTemplate jdbcTemplate;

    public PersonImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Person person) {
        String query = "insert into person(full_name,date_birth,nationality,phone_number,email) " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(query, person.getFullName(),
                person.getDateOfBirth(),
                person.getNationality(),
                person.getPhoneNumber(),
                person.getEmail());
    }

    @Override
    public List<Person> getAllData() {
        String query = "select * from person";
        return jdbcTemplate.query(query, new PersonRowMapper());
    }

    @Override
    public Person findById(int id) {
        String query = "select * from person where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new PersonRowMapper());
    }

    @Override
    public void updatePerson(Person person, int id) {
        String query = "update person set full_name=?, date_birth=?,nationality=?,phone_number=?,email=? where id=?";
        jdbcTemplate.update(query, person.getFullName(),
                person.getDateOfBirth(),
                person.getNationality(),
                person.getPhoneNumber(),
                person.getEmail(),
                id);
    }
}

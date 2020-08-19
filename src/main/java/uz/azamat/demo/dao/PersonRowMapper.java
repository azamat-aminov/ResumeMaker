package uz.azamat.demo.dao;

import org.springframework.jdbc.core.RowMapper;
import uz.azamat.demo.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFullName(resultSet.getString("full_name"));
        person.setDateOfBirth(resultSet.getDate("date_birth"));
        person.setNationality(resultSet.getString("nationality"));
        person.setPhoneNumber(resultSet.getString("phone_number"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}

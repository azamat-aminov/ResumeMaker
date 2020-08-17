package uz.azamat.demo.dao;

import uz.azamat.demo.model.Person;

import java.util.List;

public interface PersonDao {
    void save(Person person);

    List<Person> getAllData();

    Person findById(int id);

    void update(Person person, int id);
    void delete(int id);
}

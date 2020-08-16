package uz.azamat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.azamat.demo.dao.PersonDao;
import uz.azamat.demo.model.Person;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonDao personDao;

    public void save(Person person) {
        personDao.save(person);
    }

    public List<Person> getAllData() {
        return personDao.getAllData();
    }

    public Person findById(int id) {
        return personDao.findById(id);
    }
}

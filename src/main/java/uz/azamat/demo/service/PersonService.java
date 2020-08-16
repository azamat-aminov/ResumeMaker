package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.dao.PersonDao;
import uz.azamat.demo.model.Person;

import java.util.List;

@Service
public class PersonService {
    PersonDao personDao;

    public void save(Person person) {
        personDao.save(person);
    }

    List<Person> getAllData() {
        return personDao.getAllData();
    }
}

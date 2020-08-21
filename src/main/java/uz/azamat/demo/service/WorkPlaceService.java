package uz.azamat.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.azamat.demo.dao.WorkPlaceDao;
import uz.azamat.demo.model.WorkPlace;

import java.util.List;

@Service
public class WorkPlaceService {
    @Autowired
    WorkPlaceDao workPlaceDao;

    public void save(List<WorkPlace> workPlaces, int id) {
        workPlaceDao.save(workPlaces, id);
    }
}

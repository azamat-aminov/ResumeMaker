package uz.azamat.demo.dao;

import uz.azamat.demo.model.WorkPlace;

import java.util.List;

public interface WorkPlaceDao {
    void save(List<WorkPlace> workPlaces, int key);


    List<WorkPlace> getWorkPlacesById(int id);
}

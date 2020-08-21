package uz.azamat.demo.dao;

import org.springframework.jdbc.core.RowMapper;
import uz.azamat.demo.model.WorkPlace;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkPlaceRowMapper implements RowMapper<WorkPlace> {
    @Override
    public WorkPlace mapRow(ResultSet resultSet, int i) throws SQLException {
        WorkPlace workPlace = new WorkPlace();
        workPlace.setWorkPlaceId(resultSet.getInt("workplace_id"));
        workPlace.setWorkPlaceName(resultSet.getString("name"));
        return workPlace;
    }
}

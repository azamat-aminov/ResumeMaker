package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;
@Data
public class EducationDegree {
    private String universityName;
    private Date graduatedYear;
    private String degree;
    private int id;
}

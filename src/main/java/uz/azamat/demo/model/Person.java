package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Person {
    private int id;
    private String fullName;
    private Date dateOfBirth;
    private String nationality;
    private List<EducationDegree> educationDegrees;
    private int phoneNumber;
    private String email;
}

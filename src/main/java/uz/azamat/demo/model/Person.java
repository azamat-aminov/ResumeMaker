package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Person {
    private int id;
    private String fullName;
    private Date dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String email;
}

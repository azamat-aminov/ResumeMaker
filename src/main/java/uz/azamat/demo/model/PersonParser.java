package uz.azamat.demo.model;

import lombok.Data;

import java.sql.Date;
@Data
public class PersonParser {
    private String name;
    private Date date;
    private String nation;
    private int phone;
    private String emailPerson;
}

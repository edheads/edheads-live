package com.example.demo.models;


import com.example.demo.models.enums.GradeRange;
import com.example.demo.models.enums.USAState;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Teacher extends User {

    private String description;
    private String schoolOrganization;
    private String district;
    private String city;

    private GradeRange gradeRange;

    public Teacher() {
    }

}
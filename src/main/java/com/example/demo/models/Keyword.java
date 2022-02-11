package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue
    private Long id;

    private String displayValue;
    private String codeValue;


    public Keyword() {
    }

    public Keyword(String displayValue, String codeValue) {

        this.displayValue = displayValue;
        this.codeValue = codeValue;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}

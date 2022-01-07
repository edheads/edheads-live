package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Keyword {

    @Id
    @GeneratedValue
    private Long id;

    private String word;

    public Keyword() {
    }

    public Keyword(String word) {
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
}

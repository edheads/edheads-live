package com.example.demo.controllers;


import com.example.demo.models.enums.USAState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class EnumsRestController {

    @RequestMapping("/fetch-all-USAStates")
    public Collection<String> getAllStatesList() {
        Collection<String> listOfStates = new ArrayList<>();

        for (USAState state : USAState.values()) {
            listOfStates.add(state.toString());
        }
        return listOfStates;
    }

}

package com.example.demo;

import com.example.demo.models.Game;
import com.example.demo.models.enums.GradeRange;
import com.example.demo.repositories.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.example.demo.models.enums.GradeRange.four_6;


@Component
public class Populator implements CommandLineRunner {

    @Resource
    GameRepository gameRepo;



    @Override
    public void run(String... args) throws Exception {


    }
}

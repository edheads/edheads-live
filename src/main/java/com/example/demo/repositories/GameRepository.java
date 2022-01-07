package com.example.demo.repositories;

import com.example.demo.models.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface GameRepository extends CrudRepository<Game, Long> {

    Game findById(String id);

    Game findByGameLink(String gameLink);
}

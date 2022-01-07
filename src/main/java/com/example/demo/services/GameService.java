package com.example.demo.services;

import com.example.demo.models.Game;
import com.example.demo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {

        var it = gameRepository.findAll();

        var games = new ArrayList<Game>();
        it.forEach(e -> games.add(e));

        return games;
    }

    public Game findById(String id) {

        var game = gameRepository.findById(id);

        return game;
    }

    public Game findByGameLink(String gameLink) {

        var game = gameRepository.findByGameLink(gameLink);

        return game;
    }

    public Long count() {
        return gameRepository.count();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public void deleteById(Long gameId) {
        gameRepository.deleteById(gameId);
    }

}
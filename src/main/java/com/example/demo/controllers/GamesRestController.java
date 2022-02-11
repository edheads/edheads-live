package com.example.demo.controllers;


import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.Game;
import com.example.demo.repositories.GameRepository;
import com.example.demo.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;


//For reference:
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

@Controller
public class GamesRestController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads/games";

    @Autowired
    private GameService gameService;

    @Resource
    GameRepository gameRepo;

    //INDEX
    @RequestMapping(value="/games")
    public ModelAndView getAllGames(Model model) {
        String role = getLoggedInUserRole();

        Collection<Game> games = (Collection<Game>)gameRepo.findAll();
        ModelAndView mv = new ModelAndView("games/games-index");//setting view name here
        mv.addObject("games", games);
        mv.addObject("role", role);
        return mv;
    }

    //SHOW (gameLink)
    @RequestMapping(value="/games/{gameLink}")
    public ModelAndView getGameByGameLink(@PathVariable String gameLink) {
        String role = getLoggedInUserRole();

        Game games = (Game)gameRepo.findByGameLink(gameLink);
        ModelAndView mv = new ModelAndView("games/games-show");
        mv.addObject("games", games);
        mv.addObject("role", role);
        return mv;
    }


    //NEW (form)
    @RequestMapping("/games/new")
    public String showNewGameForm(Model model) {
        String role = getLoggedInUserRole();

        model.addAttribute("game", new Game());
        model.addAttribute("role", role);
        return "games/games-new";
    }

    //NEW (submit)
    @PostMapping("/games")
    public String addGame(@ModelAttribute("game") Game game, @RequestParam MultipartFile gameImage, @RequestParam MultipartFile playImage) {

        Path gameImagePath = Paths.get(uploadDirectory, gameImage.getOriginalFilename());
        try {
            Files.write(gameImagePath, gameImage.getBytes());
            System.out.println(gameImagePath.getFileName().toString());
            game.setGameImage(gameImagePath.getFileName().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path playImagePath = Paths.get(uploadDirectory, playImage.getOriginalFilename());
        try {
            Files.write(playImagePath, playImage.getBytes());
            System.out.println(playImagePath.getFileName().toString());
            game.setPlayImage(playImagePath.getFileName().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameService.saveGame(game);
        return "/games/games-index";
    }

    //EDIT (form)
    @RequestMapping("/games/{gameLink}/edit")
    public ModelAndView showEditGameForm(@PathVariable String gameLink) {
        String role = getLoggedInUserRole();

        Game game = (Game)gameRepo.findByGameLink(gameLink);
        ModelAndView mv = new ModelAndView("games/games-edit");
        mv.addObject("game", game);
        mv.addObject("role", role);
        return mv;
    }

    //EDIT (submit)
    @PostMapping("/games/edit")
    public String editGame(@ModelAttribute Game gameToEdit) throws IOException {
        String role = getLoggedInUserRole();

        Game newGame = new Game(
                gameToEdit.getGameTitle(),
                gameToEdit.getGameDescription(),
                gameToEdit.getTargetGradeRange(),
                gameToEdit.getGameLink(),
                gameToEdit.getLaunchLink()
        );

        gameRepo.save(newGame);
        return "/games/games-index"; // (Collection<Game>) gameRepo.findAll();

    }

    //DELETE
    @PostMapping("/games/delete")
    public String deleteGame(@ModelAttribute Game gameToDelete) throws IOException {
        if (gameRepo.existsById(gameToDelete.getId())) {
            gameRepo.deleteById(gameToDelete.getId());
        }
        return "/games/games-index"; // (Collection<Game>) gameRepo.findAll();
    }

    public String getLoggedInUserRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = "";
        if (principal instanceof CustomUserDetails) {
            role = ((CustomUserDetails)principal).getRole();
        } else {
            role = principal.toString();
        }

        return role;
    }

}

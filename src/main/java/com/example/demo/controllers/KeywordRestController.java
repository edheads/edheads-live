package com.example.demo.controllers;


import com.example.demo.models.Keyword;
import com.example.demo.repositories.KeywordRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
public class KeywordRestController {


    @Resource
    KeywordRepository keywordRepo;

    @RequestMapping("/fetch-all-keywords")
    public Collection<Keyword> getAllKeywordsList() {
        return (Collection<Keyword>) keywordRepo.findAll();
    }

    @PostMapping("/add-keyword")
    public Collection<Keyword> addKeywordToDatabase(@RequestBody Keyword keywordToAdd) {

        if (!keywordRepo.existsByWord(keywordToAdd.getWord())) {
            keywordRepo.save(keywordToAdd);
        }
        return (Collection<Keyword>) keywordRepo.findAll();
    }

    @PostMapping("/delete-keyword")
    public Collection<Keyword> deleteKeywordToDatabase(@RequestBody Keyword keywordToDelete) {

        if (keywordRepo.existsById(keywordToDelete.getId())) {
            keywordRepo.deleteById(keywordToDelete.getId());
        }
        return (Collection<Keyword>) keywordRepo.findAll();
    }

}

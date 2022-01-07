package com.example.demo.repositories;

import com.example.demo.models.Keyword;
import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    boolean existsByWord(String word);

}

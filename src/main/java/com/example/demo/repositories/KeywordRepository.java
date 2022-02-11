package com.example.demo.repositories;

import com.example.demo.models.Game;
import com.example.demo.models.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Long> {

    Collection<Keyword> findAll();
    Collection<Keyword> findByOrderByDisplayValueAsc();
    boolean existsByDisplayValue(String displayValue);

}

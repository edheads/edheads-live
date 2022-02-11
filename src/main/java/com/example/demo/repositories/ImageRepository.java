package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Image;

import java.util.Collection;
import java.util.stream.Stream;

@Repository
public interface ImageRepository extends CrudRepository<Image, String> {

    Image save(Image image);

    Collection<Image> findAllByParentId(Long parentId);
}

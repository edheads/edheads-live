package com.example.demo.repositories;

import com.example.demo.models.MediaManagement;
import org.springframework.data.repository.CrudRepository;

public interface MediaManagementRepository extends CrudRepository<MediaManagement, Long> {

    boolean existsByAdTagUrl(String adTagUrl);

}

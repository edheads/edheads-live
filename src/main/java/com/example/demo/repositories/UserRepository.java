package com.example.demo.repositories;

import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findById(Long id);

    Collection<User> findAllByRoleOrderByDateCreated(String role);

    Collection<User> findByApprovedByAdminTrue();
}

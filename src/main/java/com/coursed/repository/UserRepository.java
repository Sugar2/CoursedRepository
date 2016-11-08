package com.coursed.repository;

import com.coursed.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Hexray on 16.10.2016.
 * Edited by Trach on 07.11.2016
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByEmail(String email);
}
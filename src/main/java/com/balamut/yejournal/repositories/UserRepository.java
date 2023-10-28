package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}

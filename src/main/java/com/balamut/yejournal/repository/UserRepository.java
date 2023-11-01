package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    void deleteById(@NotNull UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}

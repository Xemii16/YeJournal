package com.balamut.yejournal.rest.repository;

import com.balamut.yejournal.rest.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}

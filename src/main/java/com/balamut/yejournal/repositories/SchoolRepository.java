package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.School;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchoolRepository extends CrudRepository<School, UUID> {
}

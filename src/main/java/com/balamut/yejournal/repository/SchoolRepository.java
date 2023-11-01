package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.School;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchoolRepository extends CrudRepository<School, UUID> {
}

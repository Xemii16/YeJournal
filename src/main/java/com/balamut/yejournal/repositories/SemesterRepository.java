package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SemesterRepository extends CrudRepository<Semester, UUID> {
}

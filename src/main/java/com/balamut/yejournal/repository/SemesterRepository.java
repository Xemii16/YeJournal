package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.Semester;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SemesterRepository extends CrudRepository<Semester, UUID> {
}

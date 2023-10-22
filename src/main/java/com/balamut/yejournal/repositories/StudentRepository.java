package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {
}

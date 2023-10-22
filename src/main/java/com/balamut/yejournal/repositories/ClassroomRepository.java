package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.Classroom;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClassroomRepository extends CrudRepository<Classroom, UUID> {
}

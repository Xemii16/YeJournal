package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.Classroom;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClassroomRepository extends CrudRepository<Classroom, UUID> {
}

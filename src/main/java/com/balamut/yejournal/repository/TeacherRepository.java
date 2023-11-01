package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
}

package com.balamut.yejournal.school.repository;

import com.balamut.yejournal.school.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherRepository extends CrudRepository<Teacher, UUID> {
}

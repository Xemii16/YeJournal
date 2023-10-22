package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {
}

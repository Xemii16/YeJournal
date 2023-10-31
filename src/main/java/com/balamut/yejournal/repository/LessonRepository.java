package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {
}

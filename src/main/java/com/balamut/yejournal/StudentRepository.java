package com.balamut.yejournal;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentRepository extends CrudRepository<Student, UUID> {

    boolean existsByUuid(UUID uuid);

    Student getByUuid(UUID uuid);
}

package com.balamut.yejournal.school.repository;

import com.balamut.yejournal.school.School;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchoolRepository extends CrudRepository<School, UUID> {

    @NotNull Optional<School> findById(@NotNull UUID id);
}

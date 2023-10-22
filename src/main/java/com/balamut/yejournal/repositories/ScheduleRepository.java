package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ScheduleRepository extends CrudRepository<Schedule, UUID> {
}

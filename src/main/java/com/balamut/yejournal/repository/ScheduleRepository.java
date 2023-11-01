package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ScheduleRepository extends CrudRepository<Schedule, UUID> {
}

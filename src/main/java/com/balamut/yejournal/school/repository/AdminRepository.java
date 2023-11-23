package com.balamut.yejournal.school.repository;

import com.balamut.yejournal.school.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdminRepository extends CrudRepository<Admin, UUID> {
}

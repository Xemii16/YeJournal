package com.balamut.yejournal.repositories;

import com.balamut.yejournal.entities.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdminRepository extends CrudRepository<Admin, UUID> {
}

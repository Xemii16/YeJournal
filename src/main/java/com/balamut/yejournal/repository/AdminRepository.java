package com.balamut.yejournal.repository;

import com.balamut.yejournal.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdminRepository extends CrudRepository<Admin, UUID> {
}

package com.balamut.yejournal.authentication.repository;

import com.balamut.yejournal.authentication.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
}

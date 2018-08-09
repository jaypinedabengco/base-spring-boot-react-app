package com.bengco.app.react.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bengco.app.react.model.Role;
import com.bengco.app.react.model.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}

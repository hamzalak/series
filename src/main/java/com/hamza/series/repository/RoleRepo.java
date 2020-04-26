package com.hamza.series.repository;


import java.util.Optional;

import com.hamza.series.model.auth.ERole;
import com.hamza.series.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

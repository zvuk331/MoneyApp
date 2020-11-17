package ru.java.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.java.learn.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

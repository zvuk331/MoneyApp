package ru.java.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.java.learn.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

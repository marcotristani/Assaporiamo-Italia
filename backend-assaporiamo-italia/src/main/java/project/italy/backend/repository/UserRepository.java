package project.italy.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.italy.backend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}

package com.corelia.repository;

import com.corelia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReps extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

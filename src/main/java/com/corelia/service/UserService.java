package com.corelia.service;

import com.corelia.error.RecordNotFoundException;
import com.corelia.model.User;
import com.corelia.repository.UserReps;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserReps userReps;

    @Transactional
    public User insert(User entity) {
        return userReps.save(entity);
    }

    public User findByEmail(String email) {
        return userReps.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("Record Not Found Exception"));
    }

    public Optional<User> getByEmail(String email) {
        return userReps.findByEmail(email);
    }

    public User findById(Long id) {
        return userReps.findById(id).orElseThrow(() -> new RecordNotFoundException("Record Not Found Exception"));
    }

    public Optional<User> getById(Long id) {
        return userReps.findById(id);
    }
}

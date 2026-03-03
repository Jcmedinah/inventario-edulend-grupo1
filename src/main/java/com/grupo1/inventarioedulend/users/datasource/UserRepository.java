package com.grupo1.inventarioedulend.users.datasource;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo1.inventarioedulend.users.models.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

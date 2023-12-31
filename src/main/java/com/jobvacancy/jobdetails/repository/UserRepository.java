package com.jobvacancy.jobdetails.repository;

import com.jobvacancy.jobdetails.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUsername(String username);
}

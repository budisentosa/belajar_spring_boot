package com.example.berzkoder02.models.repos;

import com.example.berzkoder02.models.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findAppUserByEmail(String email);
}

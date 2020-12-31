package com.insightleave.userservice.repositories;

import com.insightleave.userservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(String email);

    List<UserEntity> deleteUserEntityByEmail(String email);
}

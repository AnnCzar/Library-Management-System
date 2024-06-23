package org.example.technologie_sieciowe_1.infrastructure.repositories;

import org.example.technologie_sieciowe_1.commonTypes.UserRole;
import org.example.technologie_sieciowe_1.infrastructure.entity.AuthEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends CrudRepository<AuthEntity, Integer> {

    Optional<AuthEntity> findByUsername(String username);

    Optional<Object> findByUser(UserEntity userEntity);
    List<AuthEntity> findAllByRole(UserRole role);

    Optional<Object> findByUserId(Integer userId);
}


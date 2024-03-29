package org.example.technologie_sieciowe_1.infrastructure.repositories;

import org.example.technologie_sieciowe_1.infrastructure.entity.AuthEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<AuthEntity, Integer> {

    Optional<AuthEntity> findByUserName(String userName);
}

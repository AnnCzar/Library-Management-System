package org.example.technologie_sieciowe_1.infrastructure.repositories;

import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {


    UserEntity findByUsername(String username);

}
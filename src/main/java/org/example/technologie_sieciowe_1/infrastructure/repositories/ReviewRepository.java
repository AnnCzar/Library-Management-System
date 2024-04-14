package org.example.technologie_sieciowe_1.infrastructure.repositories;

import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByUserId(Integer userId);

}

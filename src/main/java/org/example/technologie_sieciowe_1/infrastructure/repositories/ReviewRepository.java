package org.example.technologie_sieciowe_1.infrastructure.repositories;

import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Integer> {
}

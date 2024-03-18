package org.synoms.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.SpecificationEntity;

public interface SpecificationRepository extends MongoRepository<SpecificationEntity, String> {
}

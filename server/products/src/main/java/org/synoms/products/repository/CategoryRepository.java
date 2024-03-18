package org.synoms.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.CategoryEntity;

public interface CategoryRepository extends MongoRepository<CategoryEntity,String> {
}

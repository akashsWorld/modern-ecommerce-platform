package org.synoms.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.Category;

public interface CategoryRepository extends MongoRepository<Category,String> {
}

package org.synoms.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ProductImages,String> {
}

package org.synoms.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.ProductImages;

public interface ImageRepository extends MongoRepository<ProductImages,String> {
}

package org.synoms.products;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.ProductsEntity;

public interface ProductsRepository extends MongoRepository<ProductsEntity,String> {
}

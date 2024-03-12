package org.synoms.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.Category;
import org.synoms.products.entity.ProductsEntity;

import java.util.List;

public interface ProductsRepository extends MongoRepository<ProductsEntity,String> {

    List<ProductsEntity> findAllBySearchTagLineLike(String searchTagLine);

    List<ProductsEntity> findAllByCategoriesContains(Category categories);


}

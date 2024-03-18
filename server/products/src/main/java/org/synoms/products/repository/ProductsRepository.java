package org.synoms.products.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.ProductsEntity;

import java.util.List;

public interface ProductsRepository extends MongoRepository<ProductsEntity,String> {

    Page<ProductsEntity> findBySearchTagLineIgnoreCaseLike(String searchTagLine, Pageable pageable);

    Page<ProductsEntity> findByCategoriesContains(List<CategoryEntity> categories, Pageable pageable);

}

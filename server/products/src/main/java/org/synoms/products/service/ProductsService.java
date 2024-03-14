package org.synoms.products.service;

import org.springframework.data.domain.Page;
import org.synoms.client.products.CategoryListDTO;
import org.synoms.client.products.ProductDTO;

import java.util.List;

public interface ProductsService {
    CategoryListDTO getAllCategories();
    String saveProduct(ProductDTO productDTO);
    Page<ProductDTO> getProducts(List<String> category, String searchTagLine, Integer pageNumber, Integer pageSize,String fieldName);

}

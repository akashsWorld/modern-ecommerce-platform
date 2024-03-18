package org.synoms.products.service;

import org.springframework.data.domain.Page;
import org.synoms.client.products.CategorySpecificationListDTO;
import org.synoms.client.products.ProductDTO;

import java.util.List;

public interface ProductsService {
    CategorySpecificationListDTO getAllCategoriesAndSpecificationNames();
    String saveProduct(ProductDTO productDTO);
    Page<ProductDTO> getProducts(List<String> category, String searchTagLine, Integer pageNumber, Integer pageSize,String fieldName);
    ProductDTO getProductById(String productId);
}

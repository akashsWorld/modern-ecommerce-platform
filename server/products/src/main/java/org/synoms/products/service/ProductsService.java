package org.synoms.products.service;

import org.springframework.data.domain.Page;
import org.synoms.client.products.DTOS.CategoryResponse;
import org.synoms.client.products.DTOS.ProductDTO;

import java.util.List;

public interface ProductsService {
    CategoryResponse getAllCategories();
    List<String> saveProducts(List<ProductDTO> productDTO);
    Page<ProductDTO> getProducts(List<String> category, String searchTagLine, Integer pageNumber, Integer pageSize,String fieldName);
    ProductDTO getProductById(String productId);
}

package org.synoms.products.service;

import org.synoms.client.products.CategoryResponse;
import org.synoms.client.products.ProductDTO;

public interface ProductsService {
    CategoryResponse getAllCategories();

    String saveProduct(ProductDTO productDTO);
}

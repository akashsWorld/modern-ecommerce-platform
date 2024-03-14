package org.synoms.products.service;

import org.synoms.client.products.CategoryListDTO;
import org.synoms.client.products.ProductDTO;
import org.synoms.client.products.ProductListDTO;

import java.util.List;

public interface ProductsService {
    CategoryListDTO getAllCategories();
    String saveProduct(ProductDTO productDTO);
    ProductListDTO getProducts(List<String> category, String searchTagLine);
}

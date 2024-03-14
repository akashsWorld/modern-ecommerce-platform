package org.synoms.products.util;


import org.synoms.client.products.ProductDTO;
import org.synoms.products.entity.ProductsEntity;

public class DTOConverter {

    public static ProductDTO convertToProductDTO(ProductsEntity productEntity){
        return new ProductDTO(
                productEntity.getProductId(),
                productEntity.getProductName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getDiscount(),
                null,
                null,
                null,
                productEntity.getLaunchDate(),
                null,
                null);
    }

}

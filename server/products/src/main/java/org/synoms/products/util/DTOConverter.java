package org.synoms.products.util;


import org.synoms.client.products.AccessoriesDTO;
import org.synoms.client.products.ProductDTO;
import org.synoms.products.entity.ProductsEntity;

import java.util.List;

public class DTOConverter {

    public static ProductDTO convertToProductDTO(ProductsEntity productEntity){
        return new ProductDTO(
                productEntity.getProductId(),
                productEntity.getProductName(),
                productEntity.getDescription(),
                productEntity.getProductQuantity(),
                productEntity.getPrice(),
                productEntity.getDiscount(),
                null,
                null,
                null,
                productEntity.getLaunchDate(),
                null,
                null);
    }

    public static AccessoriesDTO convertToAccessoriesDTO(List<ProductsEntity> accessories){
//        TODO: Have to implement the logic of create Accessories DTO.
        return null;
    }


}

package org.synoms.products.util;


import org.synoms.client.products.ProductDTO;
import org.synoms.client.products.SpecificationDTO;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.entity.Specification;

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
                productEntity.getLaunchDate(),
                null);
    }

    public SpecificationDTO toSpecificationDTO(Specification specification){
        return new SpecificationDTO();
    }

}

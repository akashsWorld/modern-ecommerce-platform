package org.synoms.products.util_services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.synoms.client.products.DTOS.AccessoriesDTO;
import org.synoms.client.products.DTOS.CategoryDTO;
import org.synoms.client.products.DTOS.ProductDTO;
import org.synoms.client.products.DTOS.SpecificationDTO;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.models.Specification;
import org.synoms.products.rest_services.MediaServiceClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DTOConverter {

    private final MediaServiceClient mediaServiceClient;



    public ProductDTO convertToProductDTO(ProductsEntity productEntity){


        return ProductDTO.builder()
                .productId(productEntity.getProductId())
                .productName(productEntity.getProductName())
                .description(productEntity.getDescription())
                .productPrice(productEntity.getPrice())
                .discount(productEntity.getDiscount())
                .launchDate(productEntity.getLaunchDate())
                .build();
    }

    public CategoryDTO convertToCategoryDTO(CategoryEntity categoryEntity){
        return new CategoryDTO(
                categoryEntity.getId(),
                categoryEntity.getName(),
                mediaServiceClient.getCategoryImage(categoryEntity.getId())
        );
    }

    public SpecificationDTO convertToSpecificationDTO(Specification specifications){
        return null;
    }

    public AccessoriesDTO convertToAccessoriesDTO(List<ProductsEntity> accessories){
//        TODO: Have to implement the logic of create Accessories DTO.
        return null;
    }




}

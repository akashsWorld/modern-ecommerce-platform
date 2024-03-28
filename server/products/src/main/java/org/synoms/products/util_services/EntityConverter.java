package org.synoms.products.util_services;

import lombok.RequiredArgsConstructor;
import org.synoms.client.products.DTOS.CategoryDTO;
import org.synoms.client.products.DTOS.ProductDTO;
import org.synoms.client.products.DTOS.SpecificationDTO;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.models.Specification;
import org.synoms.products.models.SpecificationType;

@RequiredArgsConstructor
public class EntityConverter {
    private final UtilServices utilServices;

    public ProductsEntity convertToProductEntity(ProductDTO productDTO){

        String searchTagLine = utilServices.getProductTagline(productDTO);

        return ProductsEntity
                .builder()
                .productId(null)
                .productName(productDTO.productName())
                .description(productDTO.description())
                .productQuantity(productDTO.quantity())
                .productImages(null)
                .price(productDTO.productPrice())
                .discount(productDTO.discount())
                .categoryIds(productDTO.categories())
                .searchTagLine(searchTagLine)
                .specification()
                .build()
    }
    public CategoryEntity convertToCategoryEntity(CategoryDTO categoryDTO){
        return CategoryEntity.builder()
                .name(categoryDTO.name())
                .build();
    }

    public static Specification convertToSpecification(SpecificationDTO specificationDTO){
        return Specification.builder()
                .specificationName(specificationDTO.specificationName())
                .specifications(specificationDTO.specificationTypeNameValues().stream().map(specType-> SpecificationType.builder()
                        .specTypeName(specType.specificationTypeName())
                        .specTypeValue(specType.specificationTypeValue())
                        .build()).toList())
                .build();
    }


}

package org.synoms.products.util_services;


import org.springframework.stereotype.Service;
import org.synoms.client.products.DTOS.ProductDTO;
import org.synoms.client.products.DTOS.SpecificationDTO;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.models.Specification;

import java.util.List;

@Service
public class UtilServices {

    public String getProductTagline(ProductDTO productDTO){
        String productName = productDTO.getProductName();
        String description = productDTO.getDescription();
        List<String> highlights = productDTO.getHighlights();
        List<String> categories = productDTO.getCategories();

        StringBuilder tagline = new StringBuilder();

        tagline.append(productName).append(description);
        for(String category : categories){
            tagline.append(category.toLowerCase())
                    .append(" ")
                    .append(category)
                    .append(" ")
                    .append("under ")
                    .append(productDTO.getProductPrice());
        }
        for (String eachHighLights : highlights){
            tagline.append(eachHighLights);
        }

        return tagline.toString();
    }

    public List<Specification> getSpecifications(List<SpecificationDTO> specificationDTOList){
        return specificationDTOList.stream().map(EntityConverter::convertToSpecification).toList();
    }

    public String toCapitalize(String str){
        if(str == null || str.isEmpty()){
            return str;
        }
        char ch = Character.toUpperCase(str.charAt(0));
        return ch + str.substring(1);
    }

    public List<CategoryEntity> categories (List<String> categories){
        return categories.stream().map(name-> CategoryEntity.builder().name(name).build()).toList();
    }
}

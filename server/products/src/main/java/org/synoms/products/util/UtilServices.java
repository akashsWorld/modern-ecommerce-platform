package org.synoms.products.util;


import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.client.products.CategoryDTO;
import org.synoms.client.products.ProductDTO;
import org.synoms.client.products.SpecificationDTO;
import org.synoms.products.entity.Category;
import org.synoms.products.entity.ProductImages;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.entity.Specification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilServices {

    public String getProductTagline(String productName, List<CategoryDTO> categories, String description){

        StringBuilder tagline = new StringBuilder();

        tagline.append(productName);
        tagline.append(description);

        for(CategoryDTO category : categories){
            tagline.append(category.category().toLowerCase());
        }

        return tagline.toString();
    }

    public List<ProductImages> productImages(List<MultipartFile> images,String id) throws IOException {

        List<ProductImages> productImagesList = new ArrayList<>();

        int index = 1;
        for(MultipartFile multipartFile: images){

            ProductImages productImages = getProductImage(multipartFile,id,index++);

            productImagesList.add(productImages);
        }
        return productImagesList;
    }

    private ProductImages getProductImage(MultipartFile multipartFile, String id, Integer index) throws IOException {
//        TODO: create productImage objects.

        return ProductImages.builder()
                .image(new Binary(multipartFile.getBytes()))
                .imageName(id+"_image-"+index)
                .sequence(index)
                .build();
    }

    public List<CategoryDTO> convertToCategoryDTO(List<Category> categories){


        return categories.stream().map(cat-> new CategoryDTO(cat.getName())).toList();
    }

    public List<Category> convertToCategoryList(List<CategoryDTO> categoryDTOS){
        return categoryDTOS.stream().map(categoryDTO -> Category.builder()
                .name(toCapitalize(categoryDTO.category()))
                .categorySearchName(categoryDTO.category().toLowerCase())
                .build()).toList();
    }

    public String toCapitalize(String str){
        if(str == null || str.isEmpty()){
            return str;
        }
        char ch = Character.toUpperCase(str.charAt(0));
        return ch + str.substring(1);
    }

    public List<Category> categories (List<String> categories){
        return categories.stream().map(name-> Category.builder().name(name).build()).toList();
    }

    public List<ProductDTO> convertToProductDTO(List<ProductsEntity> productsEntities){
        return productsEntities.stream().map(cc-> new ProductDTO(
                cc.getProductId(),
                cc.getProductName(),
                cc.getDescription(),
                cc.getPrice(),
                cc.getDiscount(),
                null,
                null,
                cc.getLaunchDate())).toList();
    }

    public SpecificationDTO toSpecificationDTO(Specification specification){
        return new SpecificationDTO();
    }


}

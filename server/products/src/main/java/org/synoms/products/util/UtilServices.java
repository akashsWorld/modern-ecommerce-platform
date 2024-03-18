package org.synoms.products.util;


import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.client.products.CategoryNameDTO;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.ProductImages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilServices {

    public String getProductTagline(String productName, List<String> categories, String description){

        StringBuilder tagline = new StringBuilder();

        tagline.append(productName);
        tagline.append(description);

        for(String category : categories){
            tagline.append(category.toLowerCase());
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



    public List<CategoryEntity> convertToCategoryList(List<String> categoryDTOS){
        return categoryDTOS.stream().map(categoryDTO -> CategoryEntity.builder()
                .name(toCapitalize(categoryDTO))
                .categorySearchName(categoryDTO.toLowerCase())
                .build()).toList();
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

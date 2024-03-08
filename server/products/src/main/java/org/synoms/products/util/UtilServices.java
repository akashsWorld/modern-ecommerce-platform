package org.synoms.products.util;


import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.client.products.Category;
import org.synoms.products.entity.ProductImages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtilServices {

    public String getProductTagline(String productName, List<Category> categories, String description){

        StringBuilder tagline = new StringBuilder();

        tagline.append(productName);
        tagline.append(description);

        for(Category category : categories){
            tagline.append(category.name());
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

}

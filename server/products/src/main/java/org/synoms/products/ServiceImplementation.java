package org.synoms.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.products.entity.Category;
import org.synoms.client.products.CategoryResponse;
import org.synoms.client.products.ProductDTO;
import org.synoms.products.entity.ProductImages;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.exception.ProductNotFountException;
import org.synoms.products.repository.CategoryRepository;
import org.synoms.products.repository.ImageRepository;
import org.synoms.products.repository.ProductsRepository;
import org.synoms.products.service.ImageService;
import org.synoms.products.service.ProductsService;
import org.synoms.products.util.UtilServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceImplementation implements ProductsService, ImageService {

    private final UtilServices utilServices;
    private final ProductsRepository productsRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return new CategoryResponse(
                categories.size(),
                utilServices.convertToCategoryDTO(categories)
        );
    }

    @Override
    public String saveProduct(ProductDTO productDTO) {

        List<Category> categories = utilServices.convertToCategoryList(productDTO.categories());

        List<Category> newCategories = categories.stream().filter(category -> !categoryRepository.exists(Example.of(category))).toList();

        categoryRepository.saveAll(newCategories);


        String tagline = utilServices.getProductTagline(
                productDTO.productName(),
                productDTO.categories(),
                productDTO.description()
        );



        ProductsEntity product = ProductsEntity.builder()
                .productName(productDTO.productName())
                .description(productDTO.description())
                .price(productDTO.productPrice())
                .discount(productDTO.discount())
                .categories(categories)
                .searchTagLine(tagline)
                .launchDate(productDTO.launchDate())
                .build();

        return productsRepository.save(product).getProductId();
    }

    @Override
    public void saveImages(String id, List<MultipartFile> images) throws IOException {
        Optional<ProductsEntity> product = productsRepository.findById(id);


        if(product.isEmpty()){
            throw new ProductNotFountException("ProductNot found to save images");
        }
//        TODO: have to implement the save part for product.

        List<ProductImages> productImagesList =  utilServices.productImages(images,id);

        List<ProductImages> proImagesFormDB = imageRepository.saveAll(productImagesList);

        ProductsEntity productsEntity = product.get();
        productsEntity.setProductImages(proImagesFormDB);
        productsRepository.save(productsEntity);

    }
}

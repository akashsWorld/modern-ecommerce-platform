package org.synoms.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.client.orders.RatingDTO;
import org.synoms.client.products.CategoryNameDTO;
import org.synoms.client.products.CategorySpecificationListDTO;
import org.synoms.client.products.ProductDTO;
import org.synoms.client.products.SpecificationNameDTO;
import org.synoms.products.config.OrderServiceClient;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.ProductImages;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.entity.SpecificationEntity;
import org.synoms.products.exception.ImageNotFoundException;
import org.synoms.products.exception.ProductNotFountException;
import org.synoms.products.exception.SearchParamException;
import org.synoms.products.repository.CategoryRepository;
import org.synoms.products.repository.ImageRepository;
import org.synoms.products.repository.ProductsRepository;
import org.synoms.products.repository.SpecificationRepository;
import org.synoms.products.service.ImageService;
import org.synoms.products.service.ProductsService;
import org.synoms.products.util.ConvertToList;
import org.synoms.products.util.DTOConverter;
import org.synoms.products.util.UtilServices;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceImplementation implements ProductsService, ImageService {

    private final UtilServices utilServices;
    private final ProductsRepository productsRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final OrderServiceClient orderServiceClient;
    private final SpecificationRepository specificationRepository;
    private final ConvertToList convertToList;

    @Override
    public CategorySpecificationListDTO getAllCategoriesAndSpecificationNames () {

        List<CategoryEntity> categories = categoryRepository.findAll();
        List<SpecificationEntity> specifications = specificationRepository.findAll();

        return new CategorySpecificationListDTO(
                convertToList.convertCategoryEntityToNameList(categories),
                convertToList.convertSepcificationToNameList(specifications)
        );
    }

    @Override
    public String saveProduct(ProductDTO productDTO) {

        List<CategoryEntity> categories = utilServices.convertToCategoryList(productDTO.categories());
        
        List<CategoryEntity> newCategories = categories.stream().filter(category -> !categoryRepository.exists(Example.of(category))).toList();


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
    public Page<ProductDTO> getProducts(List<String> categorySearchParam,
                                        String searchTagLine,
                                        Integer pageNumber,
                                        Integer pageSize,
                                        String fieldName) {
        if((categorySearchParam == null && searchTagLine == null) ||
                (categorySearchParam !=null && searchTagLine!=null)){
            throw new SearchParamException("Invalid Search Param");
        }


        final Page<ProductsEntity> productsEntities;

        final Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(Sort.Order.desc(fieldName)));

        if(categorySearchParam==null){
            productsEntities= productsRepository.findBySearchTagLineIgnoreCaseLike(searchTagLine.toLowerCase(),pageable);
        }else{
            List<CategoryEntity> categories =  utilServices.categories(categorySearchParam);
            productsEntities = productsRepository.findByCategoriesContains(categories,pageable);
        }


        return productsEntities.map(DTOConverter::convertToProductDTO);
    }

    @Override
    public ProductDTO getProductById(String productId) {

        Optional<ProductsEntity> productsEntityOptional = productsRepository.findById(productId);

        if(productsEntityOptional.isEmpty()){
            throw new ProductNotFountException("Invalid product");
        }
        ProductsEntity product = productsEntityOptional.get();

//        TODO: have to implement the accessories section in the product.

        List<RatingDTO> ratingDTOS = orderServiceClient.getAllRatingsByProductId(productId);



        return new ProductDTO(
                productId,
                product.getProductName(),
                product.getDescription(),
                product.getProductQuantity(),
                product.getPrice(),
                product.getDiscount(),
                null,
                null,
                product.getSpecification(),
                product.getLaunchDate(),
                ratingDTOS,
                null
        );
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

    @Override
    public byte[] getImage(String imageId) {
        ProductImages productImage = imageRepository.findById(imageId).orElseThrow(() -> new ImageNotFoundException("Image id Invalid."));
        return productImage.getImage().getData().clone();
    }
}

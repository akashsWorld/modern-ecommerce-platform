package org.synoms.products;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.synoms.client.orders.RatingDTO;
import org.synoms.client.products.DTOS.CategoryDTO;
import org.synoms.client.products.DTOS.CategoryResponse;
import org.synoms.client.products.DTOS.ProductDTO;
import org.synoms.client.products.DTOS.SpecificationDTO;
import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.ProductsEntity;
import org.synoms.products.exception.ProductNotFountException;
import org.synoms.products.exception.SearchParamException;
import org.synoms.products.repository.CategoryRepository;
import org.synoms.products.repository.ImageRepository;
import org.synoms.products.repository.ProductsRepository;
import org.synoms.products.rest_services.OrderServiceClient;
import org.synoms.products.service.ProductsService;
import org.synoms.products.util_services.DTOConverter;
import org.synoms.products.util_services.UtilServices;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceImplementation implements ProductsService {

    private final UtilServices utilServices;
    private final ProductsRepository productsRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final OrderServiceClient orderServiceClient;
    private final DTOConverter dtoConverter;

    @Override
    public CategoryResponse getAllCategories () {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(dtoConverter::convertToCategoryDTO).toList();
        return new CategoryResponse(categoryDTOS.size(),categoryDTOS);
    }

    @Override
    public List<String> saveProducts(List<ProductDTO> productDTO) {
//        TODO: implement the logic to save all the product.


        return null;
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


        return productsEntities.map(dtoConverter::convertToProductDTO);
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

        List<SpecificationDTO> specificationList = product.getSpecifications().stream().map(dtoConverter::convertToSpecificationDTO).toList();

        ProductDTO productDTO = dtoConverter.convertToProductDTO(product);
        productDTO.setSpecification(specificationList);
        productDTO.setRatings(ratingDTOS);
        return productDTO;
    }
}

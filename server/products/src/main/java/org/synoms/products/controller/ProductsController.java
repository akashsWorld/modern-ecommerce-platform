package org.synoms.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.client.products.Category;
import org.synoms.client.products.CategoryResponse;
import org.synoms.client.products.ProductDTO;
import org.synoms.client.products.ProductListDTO;
import org.synoms.products.ProductsService;
import org.synoms.products.util.PriceFilter;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductsController {

    private final ProductsService productsService;


    @GetMapping
    public ResponseEntity<ProductListDTO> getProducts(
            @RequestParam(value = "category",required = false,defaultValue = "ALL") Category category,
            @RequestParam(value = "name",required = false) String searchOn,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize",required = false,defaultValue = "20") Integer pageSize,
            @RequestParam(value = "priceFilter", required = false, defaultValue = "popular") PriceFilter priceFilter
    ){

        return null;
    }

    @PostMapping(consumes = {"application/json"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void saveProduct(@RequestBody ProductDTO product){

        System.out.println(product);

    }
    @PostMapping(value = "/saveMultiple")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void saveProduct(
            @RequestBody List<ProductDTO> productDTOS
            ){


    }

    @GetMapping(value = "/categories")
    public ResponseEntity<CategoryResponse> getAllProductCategories(){
        return ResponseEntity.ok().body(productsService.getAllCategories());
    }


}

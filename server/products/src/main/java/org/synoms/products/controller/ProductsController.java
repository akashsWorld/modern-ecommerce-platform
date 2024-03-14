package org.synoms.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synoms.client.products.CategoryListDTO;
import org.synoms.client.products.ProductDTO;
import org.synoms.client.products.ProductListDTO;
import org.synoms.products.service.ProductsService;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductsController {

    private final ProductsService productsService;


    @GetMapping
    public ResponseEntity<ProductListDTO> getProducts(
            @RequestParam(name = "category",required = false) List<String> category,
            @RequestParam(name = "searchName",required = false) String searchTagLine
    ){
        return ResponseEntity.ok(productsService.getProducts(category,searchTagLine));
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<String> saveProduct(@RequestBody ProductDTO productDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveProduct(productDTO));
    }


    @PostMapping(value = "/saveMultiple")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void saveProduct(
            @RequestBody List<ProductDTO> productDTOS
            ){


    }

    @GetMapping(value = "/categories")
    public ResponseEntity<CategoryListDTO> getAllProductCategories() {
        return ResponseEntity.ok().body(productsService.getAllCategories());
    }


}

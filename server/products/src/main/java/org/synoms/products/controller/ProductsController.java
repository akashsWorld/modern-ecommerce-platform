package org.synoms.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synoms.client.products.CategoryListDTO;
import org.synoms.client.products.ProductDTO;
import org.synoms.products.service.ProductsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductsController {

    private final ProductsService productsService;


    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(name = "category",required = false) List<String> category,
            @RequestParam(name = "searchName",required = false) String searchTagLine,
            @RequestParam(name = "page",required = false,defaultValue = "0") Integer pageNumber,
            @RequestParam(name="pageSize",required = false,defaultValue = "20") Integer pageSize,
            @RequestParam(name="fieldName",required = false,defaultValue = "launchDate") String fieldName
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                productsService.getProducts(category,searchTagLine,pageNumber,pageSize,fieldName)
        );
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

//    @GetMapping(value = "/demo")
//    public String demoEntity(@RequestBody Map<?,?> data){
//
//        data.keySet().forEach(cc->{
//            System.out.println(cc.getClass());
//        });
//
//        return "Hello World";
//    }


}

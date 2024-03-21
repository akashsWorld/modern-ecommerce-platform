package org.synoms.products.controller;

import feign.form.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.synoms.client.products.CategorySpecificationListDTO;
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


    @GetMapping(produces = {"application/json"},consumes = {"application/json"})
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

    @GetMapping(value = "/{productId}",produces = {"application/json"}, consumes = {"application/json"} )
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String productId){
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProductById(productId));
    }

    @PostMapping(consumes = {"application/json"} , produces = {"application/json"})
    public ResponseEntity<List<String>> saveProduct(
            @RequestBody List<ProductDTO> productDTOS
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveProducts(productDTOS));
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<CategorySpecificationListDTO> getAllProductCategoriesAndSpecificationNames() {
        return ResponseEntity.ok().body(productsService.getAllCategoriesAndSpecificationNames());
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

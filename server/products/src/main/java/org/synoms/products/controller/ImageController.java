package org.synoms.products.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(value = "/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    @PostMapping(value = "/{id}",consumes = {"multipart/form-data"})
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    public void saveProductImages(@PathVariable(value = "id") String id ,
                                  @RequestParam(name = "productImages")List<MultipartFile> images){

//        TODO: implement the service to save the image.

    }
}

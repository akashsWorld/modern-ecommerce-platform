package org.synoms.products.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.synoms.products.service.ImageService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/images")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/{id}",consumes = {"multipart/form-data"})
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    public void saveProductImages(@PathVariable(value = "id") String id ,
                                  @RequestParam(name = "productImages") List<MultipartFile> images) throws IOException, InterruptedException {

        imageService.saveImages(id,images);
    }

    @GetMapping(value = "/{imageId}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImages(@PathVariable String imageId){
        return ResponseEntity.ok(imageService.getImage(imageId));
    }
}

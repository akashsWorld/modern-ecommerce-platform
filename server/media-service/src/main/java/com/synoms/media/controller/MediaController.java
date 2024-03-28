package com.synoms.media.controller;

import com.synoms.media.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/images")
@RequiredArgsConstructor
public class MediaController {

    private final ImageService imageService;

    @PostMapping(consumes = "application/json" ,produces = "application/json")
    public void saveImage(){

    }
}

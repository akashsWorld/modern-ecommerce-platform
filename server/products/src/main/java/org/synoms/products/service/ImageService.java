package org.synoms.products.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    void saveImages(String id, List<MultipartFile> images) throws IOException;


    byte[] getImage(String imageId);
}

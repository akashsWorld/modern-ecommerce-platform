package com.synoms.media;

import com.synoms.media.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ServiceImplementation implements ImageService {
    @Override
    public byte[] getImage(String imageUrl) {
        return new byte[0];
    }
}

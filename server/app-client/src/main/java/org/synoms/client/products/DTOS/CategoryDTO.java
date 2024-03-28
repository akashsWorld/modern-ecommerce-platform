package org.synoms.client.products.DTOS;

import org.springframework.web.multipart.MultipartFile;

public record CategoryDTO (
        String id,
        String name,
        String mediaUrl
) {
}

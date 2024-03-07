package org.synoms.client.products;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductDTO(
    String productId,
    String productName,
    String description,
    Double productPrice,
    Integer discount,
    List<Category> categories,
    SpecificationDTO specification

) {
}

package org.synoms.client.products;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDTO(
    String productId,
    String productName,
    String description,
    Double productPrice,
    Integer discount,
    List<Category> categories,
    SpecificationDTO specification,
    LocalDateTime launchDate

) {
}

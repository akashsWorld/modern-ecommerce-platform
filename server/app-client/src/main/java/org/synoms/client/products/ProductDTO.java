package org.synoms.client.products;

import org.synoms.client.orders.RatingDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductDTO(
        String productId,
        String productName,
        String description,
        Double productPrice,
        Integer discount,
        List<String> highlights,
        List<CategoryDTO> categories,
        Map<String, Map<String,String>> specification,
        LocalDateTime launchDate,
        List<RatingDTO> ratings,
        List<ProductDTO> accessories
) {
}

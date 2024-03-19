package org.synoms.client.products;

import org.synoms.client.orders.RatingDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductDTO(
        String productId,
        String productName,
        String description,
        Integer quantity,
        Double productPrice,
        Integer discount,
        List<String> highlights,
        List<String> categories,
        Map<String, Map<String,String>> specification,
        LocalDateTime launchDate,
        List<RatingDTO> ratings,
        List<ProductDTO> accessories
) {
}

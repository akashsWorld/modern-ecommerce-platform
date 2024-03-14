package org.synoms.client.products;

import java.time.LocalDateTime;
import java.util.List;

public record RatingDTO(
        String buyerName,
        String buyerId,
        Double ratingNumber,
        String ratingDescription,
        LocalDateTime buyOn,
        List<String> sampleImages
) {
}

package org.synoms.client.orders;


import java.time.LocalDateTime;
import java.util.List;

public record RatingDTO(
        String userID,
        String customerName,
        Double ratingNumber,
        String comment,
        LocalDateTime rateOn,
        List<String> images
) {
}

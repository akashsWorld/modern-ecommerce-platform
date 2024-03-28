package org.synoms.client.products.DTOS;

import lombok.*;
import org.synoms.client.orders.RatingDTO;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO{
    private String productId;
    private String productName;
    private String description;
    private Integer quantity;
    private Double productPrice;
    private Integer discount;
    private List<String> highlights;
    private List<String> categories;
    private List<SpecificationDTO> specification;
    private LocalDateTime launchDate;
    private List<RatingDTO> ratings;
    private List<ProductDTO> accessories;
}

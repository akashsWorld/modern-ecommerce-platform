package org.synoms.products.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductsEntity {
    @Id
    private String productId;

    @Indexed(name = "_product_name")
    private String productName;

    @Indexed(name = "_description")
    private String description;

    @DocumentReference
    @Indexed(name = "_product_images")
    private List<ProductImages> productImages;

    @Indexed(name = "_price")
    private Double price;

    @Indexed(name = "_discount")
    private Integer discount;

    @Indexed(name = "_categories")
    private List<Category> categories;

    @Indexed(name = "_tag_line")
    private String searchTagLine;

    @Indexed(name = "_specification")
    private Specification specification;

    @Indexed(name = "_launch_date")
    private LocalDateTime launchDate;

    @Indexed(name = "_ratings")
    private List<Rating> rating;

}

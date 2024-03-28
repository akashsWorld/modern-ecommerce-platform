package org.synoms.products.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.synoms.products.models.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @Indexed(name = "_quantity")
    private Integer productQuantity;

    @Indexed(name = "_price")
    private Double price;

    @Indexed(name = "_discount")
    private Integer discount;

    @Indexed(name = "_highlights")
    private List<String> highlights;

    @Indexed(name = "_categories")
    @DocumentReference
    private List<String> categoryIds;

    @Indexed(name = "_tag_line")
    private String searchTagLine;

    @Indexed(name = "_specification")
    private List<Specification> specifications;

    @Indexed(name = "_launch_date")
    private LocalDateTime launchDate;

    @Indexed(name="_accessories")
    @DocumentReference
    private List<ProductsEntity> accessories;
}

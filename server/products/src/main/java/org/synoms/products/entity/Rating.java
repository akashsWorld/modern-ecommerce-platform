package org.synoms.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(value = "rating")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rating {
    @Id
    private String id;

    @Indexed(name = "_customer_id")
    private UUID customerId;
    @Indexed(name = "_rating_number")
    private Integer ratingNumber;
    @Indexed(name = "_comment")
    private String comment;

}

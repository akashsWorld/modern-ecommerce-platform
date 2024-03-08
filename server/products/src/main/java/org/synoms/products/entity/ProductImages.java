package org.synoms.products.entity;


import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductImages {

    @Id
    private String id;

    @Indexed(name = "_sequence")
    private Integer sequence;

    @Indexed(name = "_image_name")
    private String imageName;

    @Indexed(name = "_image")
    private Binary image;
}

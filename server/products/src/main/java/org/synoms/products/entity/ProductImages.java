package org.synoms.products.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductImages {

    @Id
    private String id;

    @Indexed(name = "_sequence")
    private Integer sequence;

    @Indexed(name = "_image")
    private Binary image;
}

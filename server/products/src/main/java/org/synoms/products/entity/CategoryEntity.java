package org.synoms.products.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    private String id;

    @Indexed(name = "_name")
    private String name;

    @Indexed(name = "_search_name")
    private String categorySearchName;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categorySearchName='" + categorySearchName + '\'' +
                '}';
    }

}

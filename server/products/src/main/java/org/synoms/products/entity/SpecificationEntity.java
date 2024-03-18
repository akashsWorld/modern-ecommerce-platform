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
public class SpecificationEntity {
    @Id
    private String id;
    @Indexed(name = "_specification_name",unique = true)
    private String specificationName;
}

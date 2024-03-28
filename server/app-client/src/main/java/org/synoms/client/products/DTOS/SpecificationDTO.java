package org.synoms.client.products.DTOS;

import java.util.List;
import java.util.Map;

public record SpecificationDTO (
        String specificationName,
        List<SpecificationTypeDTOS> specificationTypeNameValues
){
}

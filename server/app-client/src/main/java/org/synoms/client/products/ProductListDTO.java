package org.synoms.client.products;

import java.util.List;

public record ProductListDTO(
        Integer results,
        List<ProductDTO> products
) {
}

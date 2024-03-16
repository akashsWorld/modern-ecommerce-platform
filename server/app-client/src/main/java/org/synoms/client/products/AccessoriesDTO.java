package org.synoms.client.products;

public record AccessoriesDTO(
        String id,
        String accessoryOf,
        ProductDTO productDTO
) {
}

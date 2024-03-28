package org.synoms.client.products.DTOS;

public record AccessoriesDTO(
        String id,
        String accessoryOf,
        ProductDTO productDTO
) {
}

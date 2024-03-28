package org.synoms.client.products.DTOS;

import java.util.List;

public record CategoryResponse(Integer results,List<CategoryDTO> categories) {
}

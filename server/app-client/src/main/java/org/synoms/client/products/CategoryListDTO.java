package org.synoms.client.products;

import java.util.List;

public record CategoryListDTO(Integer result, List<CategoryDTO> categories) {
}

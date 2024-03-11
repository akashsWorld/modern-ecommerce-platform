package org.synoms.client.products;

import java.util.List;

public record CategoryResponse(Integer result, List<CategoryDTO> categories) {

}

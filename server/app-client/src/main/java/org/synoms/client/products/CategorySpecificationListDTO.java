package org.synoms.client.products;

import java.util.List;

public record CategorySpecificationListDTO(List<String> categoriesList,
                                            List<String> specificationList) {
}

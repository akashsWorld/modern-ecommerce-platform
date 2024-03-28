package org.synoms.products.util_services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchFilter {
    POPULAR("popular"),
    HIGH_TO_LOW("high_to_low"),
    LOW_TO_HIGH("low_to_high");
    private final String productsFieldName;
}

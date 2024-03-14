package org.synoms.products.util;


import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.Map;

public class SortOnObject {
    Sort sortOrders(Map<String, Direction> sortOrderPair){
        return Sort.by(sortOrderPair.keySet().stream().map(key-> new Order(sortOrderPair.get(key),key)).toList());
    }
}

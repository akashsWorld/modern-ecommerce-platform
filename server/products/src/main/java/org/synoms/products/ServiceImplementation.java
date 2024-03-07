package org.synoms.products;

import org.springframework.stereotype.Service;
import org.synoms.client.products.Category;
import org.synoms.client.products.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImplementation implements ProductsService {


    @Override
    public CategoryResponse getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>(List.of(Category.values()));

        return new CategoryResponse(
                categories.size(),
                categories
        );
    }
}

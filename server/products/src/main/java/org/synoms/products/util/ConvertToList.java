package org.synoms.products.util;

import org.synoms.products.entity.CategoryEntity;
import org.synoms.products.entity.SpecificationEntity;

import java.util.List;

public class ConvertToList {
    public List<String> convertCategoryEntityToNameList(List<CategoryEntity> categoryEntities){
        return categoryEntities.stream().map(CategoryEntity::getName).toList();
    }
    public List<String> convertSepcificationToNameList(List<SpecificationEntity> specificationEntities){
        return specificationEntities.stream().map(SpecificationEntity::getSpecificationName).toList();
    }
}

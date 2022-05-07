package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCategoryRequestDTO;
import org.patikadev.orderexample.dto.response.CategoryResponseDTO;
import org.patikadev.orderexample.model.Category;

public interface CategoryConverter {
    Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO);
    CreateCategoryRequestDTO categoryToCreateCategoryRequestDTO(Category category);
    CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
}

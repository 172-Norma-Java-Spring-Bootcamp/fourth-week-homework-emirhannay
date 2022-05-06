package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCategoryRequestDTO;
import org.patikadev.orderexample.model.Category;

public interface CategoryConverter {
    Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO);
    CreateCategoryRequestDTO CategoryToCreateCategoryRequestDTO(Category category);
}

package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateCategoryRequestDTO;
import org.patikadev.orderexample.model.Category;

public interface CategoryService {
    void create(CreateCategoryRequestDTO createCategoryRequestDTO);
    CreateCategoryRequestDTO getCategory(Long id);
    Category getCategoryWithId(Long id);
}

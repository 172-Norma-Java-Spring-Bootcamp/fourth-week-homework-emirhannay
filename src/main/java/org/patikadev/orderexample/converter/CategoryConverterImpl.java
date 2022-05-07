package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCategoryRequestDTO;
import org.patikadev.orderexample.dto.response.CategoryResponseDTO;
import org.patikadev.orderexample.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterImpl implements  CategoryConverter{
    @Override
    public Category toCategory(CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = new Category();
        category.setParent(createCategoryRequestDTO.parent());
        category.setName(createCategoryRequestDTO.name());
        return  category;
    }

    @Override
    public CreateCategoryRequestDTO categoryToCreateCategoryRequestDTO(Category category) {

        return new CreateCategoryRequestDTO(category.getName(), category.getParent());
    }

    @Override
    public CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName());
    }

}

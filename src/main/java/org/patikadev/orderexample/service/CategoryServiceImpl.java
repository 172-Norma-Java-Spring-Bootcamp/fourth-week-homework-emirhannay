package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.CategoryConverter;
import org.patikadev.orderexample.dto.request.CreateCategoryRequestDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Category;
import org.patikadev.orderexample.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryConverter categoryConverter;



    @Override
    public void create(CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = categoryConverter.toCategory(createCategoryRequestDTO);
        categoryRepository.save(category);
        log.info("Category created was successfully -> {}",category.getId());
    }

    @Override
    public CreateCategoryRequestDTO getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.CategoryNotFoundException("Category not found"));
        log.info("Getting category was successfully -> {}",category.getId());
        return categoryConverter.CategoryToCreateCategoryRequestDTO(category);
    }

    @Override
    public Category getCategoryWithId(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.CategoryNotFoundException("Category not found"));
        log.info("Getting category was successfully -> {}",category.getId());
        return category;
    }
}

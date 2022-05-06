package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateBrandRequestDTO;
import org.patikadev.orderexample.model.Brand;

public interface BrandService {
    void create(CreateBrandRequestDTO createBrandRequestDTO);
    CreateBrandRequestDTO getBrandDto(Long id);
    Brand getBrand(Long id);
}

package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateBrandRequestDTO;
import org.patikadev.orderexample.model.Brand;

public interface BrandConverter {
    Brand toBrand(CreateBrandRequestDTO createBrandRequestDTO);
    public CreateBrandRequestDTO BrandToCreateBrandRequestDTO(Brand brand);
}

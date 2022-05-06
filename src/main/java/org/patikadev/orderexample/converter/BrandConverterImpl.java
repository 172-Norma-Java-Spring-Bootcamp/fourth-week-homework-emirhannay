package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateBrandRequestDTO;
import org.patikadev.orderexample.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandConverterImpl implements BrandConverter{
    @Override
    public Brand toBrand(CreateBrandRequestDTO createBrandRequestDTO) {
        Brand brand = new Brand();
        brand.setName(createBrandRequestDTO.name());
        return brand;
    }
    public CreateBrandRequestDTO BrandToCreateBrandRequestDTO(Brand brand){
        return new CreateBrandRequestDTO(brand.getName());
    }
}

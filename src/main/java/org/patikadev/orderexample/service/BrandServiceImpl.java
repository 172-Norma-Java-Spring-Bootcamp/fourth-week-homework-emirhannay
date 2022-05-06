package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.BrandConverter;
import org.patikadev.orderexample.dto.request.CreateBrandRequestDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Brand;
import org.patikadev.orderexample.repository.BrandRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandConverter brandConverter;

    @Override
    public void create(CreateBrandRequestDTO createBrandRequestDTO) {
        Brand brand = brandConverter.toBrand(createBrandRequestDTO);
        brandRepository.save(brand);
        log.info("Brand was successfully created -> {}",brand.getId());
    }

    @Override
    public CreateBrandRequestDTO getBrandDto(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.BrandNotFoundException("Brand not found "));
        log.info("Getting brand was successfully -> {}",brand.getId());
        return brandConverter.BrandToCreateBrandRequestDTO(brand);

    }

    @Override
    public Brand getBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.BrandNotFoundException("Brand not found "));
        log.info("Getting brand was successfully -> {}",brand.getId());
        return brand;

    }


}

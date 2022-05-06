package org.patikadev.orderexample.validator;

import org.patikadev.orderexample.dto.request.CreateCategoryRequestDTO;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class CreateCategoryRequestValidator implements Validator<CreateCategoryRequestDTO>{
    @Override
    public void validate(CreateCategoryRequestDTO createCategoryRequestDTO) throws BaseValidationException {
        if (Objects.isNull(createCategoryRequestDTO)) {
            throw new ValidationOperationException.SellerNotValidException("Seller can not be null or empty");
        }
        if (!(StringUtils.hasLength(createCategoryRequestDTO.name()))) {
            throw new ValidationOperationException.SellerNotValidException("Category name can not be null or empty");
        }


    }
}

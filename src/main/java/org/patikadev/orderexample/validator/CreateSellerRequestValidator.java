package org.patikadev.orderexample.validator;

import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateSellerRequestValidator implements Validator<CreateSellerRequestDTO>{
    @Override
    public void validate(CreateSellerRequestDTO createSellerRequestDTO) throws BaseValidationException {
        if (Objects.isNull(createSellerRequestDTO)) {
            throw new ValidationOperationException.SellerNotValidException("Seller can not be null or empty");
        }
        if (!(StringUtils.hasText(createSellerRequestDTO.userName()))) {
            throw new ValidationOperationException.SellerNotValidException("Seller username can not be null or empty");
        }
        if (!(StringUtils.hasText(createSellerRequestDTO.password()))) {
            throw new ValidationOperationException.SellerNotValidException("Seller password can not be null or empty");
        }
        if (Objects.isNull(createSellerRequestDTO.sellerAddress())) {
            throw new ValidationOperationException.SellerNotValidException("Seller address can not be null or empty");
        }
        if (!(StringUtils.hasText(createSellerRequestDTO.createdBy()))) {
            throw new ValidationOperationException.SellerNotValidException("Seller createdBy can not be null or empty");
        }
        if (!(StringUtils.hasText(createSellerRequestDTO.email()))) {
            throw new ValidationOperationException.SellerNotValidException("Seller email can not be null or empty");
        }
        if (Objects.isNull(createSellerRequestDTO.shippingCost())) {
            throw new ValidationOperationException.SellerNotValidException("Seller shipping cost can not be null or empty");
        }

    }
}

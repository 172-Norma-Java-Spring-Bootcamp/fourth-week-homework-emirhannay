package org.patikadev.orderexample.validator;

import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;

import java.math.BigDecimal;

public class ProductPriceValidator implements Validator<BigDecimal>{
    @Override
    public void validate(BigDecimal productPrice) throws BaseValidationException {
        if(productPrice.equals(BigDecimal.ZERO)){
            throw new ValidationOperationException.ProductNotValidException("Product price can not be zero");
        }
    }
}

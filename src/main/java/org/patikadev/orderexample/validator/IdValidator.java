package org.patikadev.orderexample.validator;

import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

@Component
public class IdValidator implements Validator<Long> {
    @Override
    public void validate(Long id) throws BaseValidationException {
        if (id <= 0) {
            throw new ValidationOperationException.CustomerIDNotValidException("ID should be greater than zero.");
        }
    }
}

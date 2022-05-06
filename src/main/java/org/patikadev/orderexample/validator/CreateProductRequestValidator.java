package org.patikadev.orderexample.validator;

import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.exception.BaseValidationException;
import org.patikadev.orderexample.exception.ValidationOperationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CreateProductRequestValidator implements Validator<CreateProductRequestDTO>{
    @Override
    public void validate(CreateProductRequestDTO createProductRequestDTO) throws BaseValidationException {
        //Validate Product Info
        if(Objects.isNull(createProductRequestDTO)){
            throw new ValidationOperationException.ProductNotValidException("Product can not be null or empty");
        }
        if(Objects.isNull(createProductRequestDTO.name())){
            throw new ValidationOperationException.ProductNotValidException("Product name can not be null or empty");
        }
        if(Objects.isNull(createProductRequestDTO.price())){
            throw new ValidationOperationException.ProductNotValidException("Product price can not be null or empty");
        }
        if(Objects.isNull(createProductRequestDTO.image())){
            throw new ValidationOperationException.ProductNotValidException("Product image can not be null or empty");
        }
        if(Objects.isNull(createProductRequestDTO.brandId())){
            throw new ValidationOperationException.ProductNotValidException("Product's brand id can not be null or empty");
        }
        if(Objects.isNull(createProductRequestDTO.categoryId())){
            throw new ValidationOperationException.ProductNotValidException("Product category id can not be null or empty");
        }
        if(Objects.isNull(createProductRequestDTO.stock())){
            throw new ValidationOperationException.ProductNotValidException("Product stock can not be null or empty");
        }

    }
}

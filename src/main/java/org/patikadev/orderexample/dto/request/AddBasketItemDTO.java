package org.patikadev.orderexample.dto.request;

import javax.validation.constraints.Min;


public record AddBasketItemDTO(Long customerId, Long productId,@Min(value = 1) Long quantity) {
}

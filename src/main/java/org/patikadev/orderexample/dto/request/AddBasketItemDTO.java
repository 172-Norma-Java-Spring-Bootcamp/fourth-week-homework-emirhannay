package org.patikadev.orderexample.dto.request;

import java.math.BigDecimal;

public record AddBasketItemDTO(Long customerId, Long productId, Long quantity) {
}

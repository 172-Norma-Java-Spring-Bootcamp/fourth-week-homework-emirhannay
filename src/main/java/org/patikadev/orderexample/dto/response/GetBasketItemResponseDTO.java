package org.patikadev.orderexample.dto.response;

import java.math.BigDecimal;

public record GetBasketItemResponseDTO(String productName,
                                       Long quantity,
                                       BigDecimal price,
                                       BigDecimal discountPrice,
                                       BigDecimal taxPrice,
                                       BigDecimal shippingPrice) {
}

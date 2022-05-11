package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.dto.request.OrderAddressDTO;
import org.patikadev.orderexample.model.OrderAddress;
import org.patikadev.orderexample.model.OrderStatus;
import org.patikadev.orderexample.model.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public record GetOrdersResponseDTO(UUID orderNo,
                                   OrderStatus orderStatus,
                                   OrderAddressDTO orderAddressDTO,
                                   Date orderTime,
                                   BigDecimal price,
                                   Collection<GetBasketItemResponseDTO> itemList) {
}

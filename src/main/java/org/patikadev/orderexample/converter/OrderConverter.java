package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.dto.response.GetOrdersResponseDTO;
import org.patikadev.orderexample.model.Order;

public interface OrderConverter {
    Order toOrder(CreateOrderRequestDTO createOrderRequestDTO);
    GetOrdersResponseDTO toGetOrdersResponseDTO(Order order);
}

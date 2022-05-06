package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.model.Order;



public interface OrderService {
    void create(CreateOrderRequestDTO createOrderRequestDTO);
    Order getOrderByCustomerId(Long customerId);
    Order getOrderByBasketId(Long basketId);
}

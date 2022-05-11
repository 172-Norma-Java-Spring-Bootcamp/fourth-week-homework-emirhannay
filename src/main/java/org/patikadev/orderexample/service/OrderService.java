package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.dto.response.GetOrdersResponseDTO;
import org.patikadev.orderexample.model.Order;

import java.util.Collection;


public interface OrderService {
    void create(CreateOrderRequestDTO createOrderRequestDTO);
    Order getOrderEntityByCustomerId(Long customerId);
    Order getOrderEntityByBasketId(Long basketId);
    GetOrdersResponseDTO getOrderById(Long id);
    Collection<GetOrdersResponseDTO> getOrders();
    void delete(Long id, boolean hardDelete);
}

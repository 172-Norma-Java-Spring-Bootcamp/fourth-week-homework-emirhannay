package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.OrderConverter;
import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Order;
import org.patikadev.orderexample.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderConverter orderConverter;
    private final OrderRepository orderRepository;

    @Override
    public void create(CreateOrderRequestDTO createOrderRequestDTO) {
        //Convert DTO to ORDER
        Order order = orderConverter.toOrder(createOrderRequestDTO);
        //Check if the order has been created with the sent basket id.
        if(!Objects.isNull(getOrderByBasketId(order.getBasket().getId()))){
            throw new BusinessServiceOperationException.FailedToCreateOrderException("Failed to create order because an order has already been created with this basket id.");
        }
        orderRepository.save(order);
        log.info("Order created successfully -> {}",order.getId());
    }
    @Override
    public Order getOrderByCustomerId(Long customerId){
        Order order = orderRepository.findByCustomerId(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.OrderNotFoundException("Order Not Found")
        );
        log.info("Getting order was successfully -> {}",order.getId());
        return order;
    }

    @Override
    public Order getOrderByBasketId(Long basketId) {
        Order order = orderRepository.findByBasketId(basketId);
        log.info("Getting order was successfully -> {}",order.getId());
        return order;
    }

}

package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.OrderConverter;
import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.dto.response.GetOrdersResponseDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Order;
import org.patikadev.orderexample.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
        if(!Objects.isNull(getOrderEntityByBasketId(order.getBasket().getId()))){
            throw new BusinessServiceOperationException.FailedToCreateOrderException("Failed to create order because an order has already been created with this basket id.");
        }
        orderRepository.save(order);
        log.info("Order created successfully -> {}",order.getId());
    }
    @Override
    public Order getOrderEntityByCustomerId(Long customerId){
        Order order = orderRepository.findByCustomerId(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.OrderNotFoundException("Order Not Found")
        );
        log.info("Getting order was successfully -> {}",order.getId());
        return order;

    }

    @Override
    public Order getOrderEntityByBasketId(Long basketId) {
        Order order = orderRepository.findByBasketId(basketId);
        return order;
    }

    @Override
    public GetOrdersResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                ()-> new BusinessServiceOperationException.OrderNotFoundException("Order Not Found")
        );
        log.info("Getting order was successfully -> {}",order.getId());
        return orderConverter.toGetOrdersResponseDTO(order);
    }

    @Override
    public Collection<GetOrdersResponseDTO> getOrders() {
        Collection<GetOrdersResponseDTO> orderList = orderRepository.getOrdersByDeleteStatus(false).orElseThrow(
                () -> new BusinessServiceOperationException.OrderNotFoundException("Orders Not Found")
        )
                .stream()
                .map(orderConverter::toGetOrdersResponseDTO)
                .toList();
        return orderList;
    }

    @Override
    public void delete(Long id, boolean hardDelete) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.OrderNotFoundException("Order with this id number could not be found.")
        );
        if (hardDelete) {
            orderRepository.delete(order);
            log.info("Hard delete order was successfully -> {}",order.getId());
            return;
        }
        if (order.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Order already deleted");
        }
        order.setDeleted(true);
        orderRepository.save(order);
        log.info("Order was deleted successfully -> {}",order.getId());

    }



}

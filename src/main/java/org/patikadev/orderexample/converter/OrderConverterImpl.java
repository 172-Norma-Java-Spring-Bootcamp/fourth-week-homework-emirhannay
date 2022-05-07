package org.patikadev.orderexample.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.model.*;
import org.patikadev.orderexample.service.BasketService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class OrderConverterImpl implements OrderConverter {

    @Lazy
    private final BasketService basketService;


    @Override
    public Order toOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        //Create order entity
        Order order = new Order();
        //Find order's basket
        Basket basket = basketService.getBasketByBasketId(createOrderRequestDTO.basketId());
        //Find order's customer
        Customer customer = basket.getCustomer();
        //Set address information from user
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setOrder(order);
        orderAddress.setCity(createOrderRequestDTO.orderAddressDTO().city());
        orderAddress.setCountry(createOrderRequestDTO.orderAddressDTO().country());
        orderAddress.setDescription(createOrderRequestDTO.orderAddressDTO().description());
        orderAddress.setPhoneNumber(createOrderRequestDTO.orderAddressDTO().phoneNumber());
        orderAddress.setPostalCode(createOrderRequestDTO.orderAddressDTO().postalCode());
        order.setOrderAddress(orderAddress);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setBasket(basket);
        order.setCustomer(customer);
        order.setPrice(basket.getTotalPrice());
        order.setDeleted(false);
        order.setCreatedAt(new Date());
        order.setCreatedBy(customer.getName());
        order.setOrderTime(new Date());


        return order;
    }
}

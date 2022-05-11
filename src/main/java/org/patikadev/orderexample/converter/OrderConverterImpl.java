package org.patikadev.orderexample.converter;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.dto.request.OrderAddressDTO;
import org.patikadev.orderexample.dto.response.GetBasketItemResponseDTO;
import org.patikadev.orderexample.dto.response.GetOrdersResponseDTO;
import org.patikadev.orderexample.model.*;
import org.patikadev.orderexample.service.BasketService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OrderConverterImpl implements OrderConverter {

    @Lazy
    private final BasketService basketService;
    private final BasketItemConverter basketItemConverter;


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
        order.setOrderNo(UUID.randomUUID());

        return order;
    }
    public GetOrdersResponseDTO toGetOrdersResponseDTO(Order order) {
        Collection<GetBasketItemResponseDTO> basketItemList = order.getBasket().getItems().stream()
                .map(basketItemConverter::getBasketItemResponseDTO).toList();
        return new GetOrdersResponseDTO(order.getOrderNo(),order.getOrderStatus(),toOrderAddressDTO(order.getOrderAddress()),
                order.getOrderTime(),order.getPrice(),basketItemList);
    }
    public OrderAddressDTO toOrderAddressDTO(OrderAddress orderAddress){
        return new OrderAddressDTO(
                orderAddress.getPhoneNumber(),
                orderAddress.getCountry(),
                orderAddress.getCity(),
                orderAddress.getPostalCode(),
                orderAddress.getDescription());
    }
}

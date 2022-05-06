package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.AddBasketItemDTO;
import org.patikadev.orderexample.exception.BaseException;
import org.patikadev.orderexample.model.Basket;

import java.util.List;

public interface BasketService {

    void addBasketItemToBasket(AddBasketItemDTO addBasketItemDTO);
    List<Basket> getBasketsByCustomerId(Long customerId) throws BaseException;
    void deleteBasketItemToBasket(Long basketItemId);
    Basket getBasketWithoutOrderByCustomerId(Long customerId) throws BaseException;

    Basket getBasketByBasketId(Long basketId);
}

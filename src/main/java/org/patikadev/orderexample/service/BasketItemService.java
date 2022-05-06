package org.patikadev.orderexample.service;

import org.patikadev.orderexample.model.BasketItem;

import java.math.BigDecimal;

public interface BasketItemService {
    BigDecimal calculateBasketItemPrice(BasketItem basketItem);
    BigDecimal calculateBasketItemShippingPrice(BasketItem basketItem);
    BigDecimal calculateBasketItemDiscountPrice(BasketItem basketItem);
    BigDecimal calculateBasketItemTaxPrice(BasketItem basketItem);
    BigDecimal calculateBasketItemTotalPrice(BasketItem basketItem);
    BasketItem getBasketItem(Long id);

}

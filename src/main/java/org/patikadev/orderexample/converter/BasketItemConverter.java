package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.AddBasketItemDTO;
import org.patikadev.orderexample.model.BasketItem;

public interface BasketItemConverter {
    BasketItem toBasketItem(AddBasketItemDTO addBasketItemDTO);
}

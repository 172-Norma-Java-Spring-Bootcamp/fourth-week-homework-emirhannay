package org.patikadev.orderexample.converter;


import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.AddBasketItemDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.BasketItem;
import org.patikadev.orderexample.model.Product;
import org.patikadev.orderexample.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BasketItemConverterImpl implements BasketItemConverter {

    private final ProductRepository productRepository;

    @Override
    public BasketItem toBasketItem(AddBasketItemDTO addBasketItemDTO) {
        Product product = productRepository.findById(addBasketItemDTO.productId()).orElseThrow(
                () -> new BusinessServiceOperationException.
                        ProductNotFoundException("Product not found"));
        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setQuantity(addBasketItemDTO.quantity());
        basketItem.setPrice(BigDecimal.ZERO);
        basketItem.setDiscountPrice(BigDecimal.ZERO);
        basketItem.setShippingPrice(BigDecimal.ZERO);
        basketItem.setTaxPrice(BigDecimal.ZERO);


        return basketItem;
    }
}

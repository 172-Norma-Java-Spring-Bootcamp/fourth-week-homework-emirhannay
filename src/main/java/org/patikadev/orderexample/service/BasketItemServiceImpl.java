package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.BasketItem;
import org.patikadev.orderexample.model.Campaign;
import org.patikadev.orderexample.model.Product;
import org.patikadev.orderexample.repository.BasketItemRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class BasketItemServiceImpl implements BasketItemService{

    private final BasketItemRepository basketItemRepository;

    public BigDecimal calculateBasketItemPrice(BasketItem basketItem){
        return basketItem.getProduct().getPrice().multiply(
                BigDecimal.valueOf(basketItem.getQuantity())
        );
    }

    @Override
    public BigDecimal calculateBasketItemShippingPrice(BasketItem basketItem) {
        //Add the shipping price to the taxed price and return this
        Product product = basketItem.getProduct();
                return basketItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(basketItem.getQuantity()))
                        .add( basketItem.getPrice()
                        .multiply(basketItem.getProduct().getTaxRate())
                        .divide(BigDecimal.valueOf(100)) )
                        .add(product.getSeller().getShippingCost());
    }

    @Override
    public BigDecimal calculateBasketItemDiscountPrice(BasketItem basketItem) {
        Set<Campaign> campaignSet = basketItem.getProduct().getCampaigns();
        Campaign[] campaignArray = campaignSet.toArray(new Campaign[campaignSet.size()]);
        BigDecimal discountPrice = basketItem.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(basketItem.getQuantity()))
                .add( basketItem.getPrice()
                        .multiply(basketItem.getProduct().getTaxRate())
                        .divide(BigDecimal.valueOf(100)) )
                .add(basketItem.getProduct().getSeller().getShippingCost());

        if(basketItem.getProduct().getCampaigns().size() == 0){

        }
        else {
            //Apply campaign discount to basket item shipping price
            for(int i = 0; i < campaignSet.size(); i++){
              Campaign campaign = campaignArray[i];
                discountPrice = discountPrice.subtract(campaign.getDiscountPrice());
                discountPrice = discountPrice.multiply(BigDecimal.valueOf(100).subtract(campaign.getDiscountRate())).divide(BigDecimal.valueOf(100));
            }
        }
        //If there is a discount return discounted price
        //If there is no discount return shipping price
        return discountPrice;

    }

    @Override
    public BigDecimal calculateBasketItemTaxPrice(BasketItem basketItem) {

        BigDecimal taxRate = basketItem.getProduct().getTaxRate();
        //Apply product's tax to pure product price and return this (excluding shipping)
        return basketItem.getProduct().getPrice()
                .multiply(BigDecimal.valueOf(basketItem.getQuantity()))
                .add( basketItem.getPrice()
                .multiply(taxRate)
                .divide(BigDecimal.valueOf(100)) );
    }

    @Override
    public BigDecimal calculateBasketItemTotalPrice(BasketItem basketItem) {

        //If there is no discount return this
        if(basketItem.getDiscountPrice().intValue() == 0){
            //Return basket item shipping price
            return basketItem.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(basketItem.getQuantity()))
                    .add( basketItem.getPrice()
                            .multiply(basketItem.getProduct().getTaxRate())
                            .divide(BigDecimal.valueOf(100)) )
                    .add(basketItem.getProduct().getSeller().getShippingCost());
        }
        //If there is a discount return this
        else {
            //Return basket item discount price
            return basketItem.getDiscountPrice();
        }
    }

    @Override
    public BasketItem getBasketItem(Long id) {
        BasketItem basketItem = basketItemRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.BasketItemNotFound("Basket Item Not Found")
        );
        log.info("Getting basketItem was successfully -> {}",basketItem.getId());
        return  basketItem;
    }

}

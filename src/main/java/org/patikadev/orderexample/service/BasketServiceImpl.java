package org.patikadev.orderexample.service;

import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.BasketItemConverter;
import org.patikadev.orderexample.dto.request.AddBasketItemDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Basket;
import org.patikadev.orderexample.model.BasketItem;
import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.model.Order;
import org.patikadev.orderexample.repository.BasketItemRepository;
import org.patikadev.orderexample.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@Slf4j
public class BasketServiceImpl implements BasketService{

    private final CustomerService customerService;
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final BasketItemConverter basketItemConverter;
    private final BasketItemService basketItemService;
    @Autowired
    public BasketServiceImpl(CustomerService customerService,
                             BasketRepository basketRepository,
                             BasketItemRepository basketItemRepository,
                             BasketItemConverter basketItemConverter,
                             BasketItemService basketItemService,
                             @Lazy OrderService orderService) {
        this.customerService = customerService;
        this.basketRepository = basketRepository;
        this.basketItemRepository = basketItemRepository;
        this.basketItemConverter = basketItemConverter;
        this.basketItemService = basketItemService;
        this.orderService = orderService;
    }

    private final OrderService orderService;

    @Override
    public void addBasketItemToBasket(AddBasketItemDTO addBasketItemDTO) {
        //Find customer
        Customer customer = customerService.getCustomer(addBasketItemDTO.customerId());
        //Convert AddBasketItemDto to BasketItem
        BasketItem basketItem = basketItemConverter.toBasketItem(addBasketItemDTO);


        if(Objects.isNull(getBasketWithoutOrderByCustomerId(addBasketItemDTO.customerId()))){
            Basket basket = new Basket();
            basketItem.setBasket(basket);
            basketItem.setPrice(basketItemService.calculateBasketItemPrice(basketItem));
            basketItem.setTaxPrice(basketItemService.calculateBasketItemTaxPrice(basketItem));
            basketItem.setShippingPrice(basketItemService.calculateBasketItemShippingPrice(basketItem));
            basketItem.setDiscountPrice(basketItemService.calculateBasketItemDiscountPrice(basketItem));

            basket.setCustomer(customer);
            basket.setPrice(basketItem.getPrice());
            basket.setShippingPrice(basketItem.getShippingPrice());
            basket.setTaxPrice(basketItem.getTaxPrice());
            basket.setTotalPrice(basketItemService.calculateBasketItemTotalPrice(basketItem));
            basket.setDiscountPrice(basketItem.getDiscountPrice());
            basket.getItems().add(basketItem);
            basketRepository.save(basket);
            log.info("Basket created successfully -> {}",basket.getId());
            log.info("BasketItem {} added to basket {} successfully", basketItem.getId(), basket.getId());
        }
        else {
            Basket basket = getBasketWithoutOrderByCustomerId(addBasketItemDTO.customerId());
            basketItem.setBasket(basket);
            basketItem.setPrice(basketItemService.calculateBasketItemPrice(basketItem));
            basketItem.setTaxPrice(basketItemService.calculateBasketItemTaxPrice(basketItem));
            basketItem.setShippingPrice(basketItemService.calculateBasketItemShippingPrice(basketItem));
            basketItem.setDiscountPrice(basketItemService.calculateBasketItemDiscountPrice(basketItem));

            //Add
            basket.getItems().add(basketItem);
            basket.setPrice(basket.getPrice().add(basketItem.getPrice()));
            basket.setShippingPrice(basket.getShippingPrice().add(basketItem.getShippingPrice()));
            basket.setTaxPrice(basket.getTaxPrice().add(basketItem.getTaxPrice()));
            basket.setTotalPrice(basket.getTotalPrice().
                    add(basketItemService.calculateBasketItemTotalPrice(basketItem)));
            basket.setDiscountPrice(basket.getDiscountPrice().add(basketItemService.calculateBasketItemDiscountPrice(basketItem)));
            basketRepository.save(basket);

            log.info("BasketItem {} added to basket {} successfully", basketItem.getId(), basket.getId());
        }

    }


    @Override
    public Basket getBasketByBasketId(Long basketId) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(
                () -> new BusinessServiceOperationException.BasketNotFoundException("Basket Not Found")
        );
        log.info("Getting basket was successfully -> {}",basket.getId());
        return basket;
    }
    @Override
    public List<Basket> getBasketsByCustomerId(Long customerId) {
        List<Basket> basketList = basketRepository.getBasketByCustomerId(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.BasketNotFoundException("Basket Not Found")
        );
        log.info("Getting basket list was successfully ");
        return basketList;
    }

    @Override
    public void deleteBasketItemToBasket(Long basketItemId) {
        //Find basketItem
        BasketItem basketItem = basketItemService.getBasketItem(basketItemId);
        //Find basket
        Basket basket = getBasketByBasketId(basketItem.getBasket().getId());
        //Delete basketItem from db
        basketItemRepository.deleteById(basketItemId);
        //Calculate basket price after deletion
        calculateBasketPriceWhenDeleteItem(basketItem,basket);
        //Save basket to db
        basketRepository.save(basket);
        log.info("Basket item -> {} was successfully deleted from basket -> {}",basketItem.getId(),basket.getId());

    }



    //If the user has an active basket, return it. Otherwise, return null.
    public Basket getBasketWithoutOrderByCustomerId(Long customerId) {
        //Get list of user's baskets.
        List<Basket> basketList = getBasketsByCustomerId(customerId);

        //Necessary construct to avoid using a for loop unnecessarily if the user's cart list returns empty.
        if(basketList.size() > 0){
            //Check baskets one by one to see if they have an order
            for(int i = 0; i < basketList.size(); i++){
                Basket tempBasket = basketList.get(i);
                Order order = orderService.getOrderEntityByBasketId(tempBasket.getId());
                //Return this if you find the basket with no order
                if(Objects.isNull(order)){
                    log.info("The customer's -> active basket was found.",customerId);
                    return tempBasket;
                }
            }

        }
        log.info("The customer's -> {} active basket could not be found.",customerId);
        return null;

    }



    //It deletes the given basketItem from the cart and updates the basket price.
    public Basket calculateBasketPriceWhenDeleteItem(BasketItem basketItem, Basket basket) {
        basket.setPrice(basket.getPrice().subtract(basketItem.getPrice()));
        basket.setTaxPrice(basket.getTaxPrice().subtract(basketItem.getTaxPrice()));
        basket.setShippingPrice(basket.getShippingPrice().subtract(basketItem.getShippingPrice()));
        basket.setTotalPrice(basket.getTotalPrice().subtract(basketItemService.calculateBasketItemTotalPrice(basketItem)));
        basket.setDiscountPrice(basket.getDiscountPrice().subtract(basketItem.getDiscountPrice()));
        return basket;
    }
}

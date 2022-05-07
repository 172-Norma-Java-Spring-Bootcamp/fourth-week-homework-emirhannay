package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.AddBasketItemDTO;
import org.patikadev.orderexample.service.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/baskets")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping("addItem")
    public ResponseEntity<?> addBasketItemToBasket(@RequestBody AddBasketItemDTO addBasketItemDTO) {
            basketService.addBasketItemToBasket(addBasketItemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("basketItems/delete/{basketItemId}")
    public ResponseEntity<?> deleteBasketItemToBasket(@PathVariable Long basketItemId){
        basketService.deleteBasketItemToBasket(basketItemId);
        return ResponseEntity.ok().build();
    }





}

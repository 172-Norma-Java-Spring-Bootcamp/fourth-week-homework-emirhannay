package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateOrderRequestDTO;
import org.patikadev.orderexample.dto.response.GetOrdersResponseDTO;
import org.patikadev.orderexample.service.OrderService;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Validator<Long> idValidator;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateOrderRequestDTO createOrderRequestDTO){
        orderService.create(createOrderRequestDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete){
        orderService.delete(id,hardDelete);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<GetOrdersResponseDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetOrdersResponseDTO> getOrder(@PathVariable Long id) {
        idValidator.validate(id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}

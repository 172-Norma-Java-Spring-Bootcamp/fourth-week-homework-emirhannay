package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.service.SellerService;
import org.patikadev.orderexample.validator.CreateSellerRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/sellers")
public class SellerController {

    private final SellerService sellerService;
    private final CreateSellerRequestValidator createSellerRequestValidator;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateSellerRequestDTO createSellerRequestDTO){
            //createSellerRequestValidator.validate(createSellerRequestDTO);
            sellerService.create(createSellerRequestDTO);
            return ResponseEntity.ok().build();
    }
}

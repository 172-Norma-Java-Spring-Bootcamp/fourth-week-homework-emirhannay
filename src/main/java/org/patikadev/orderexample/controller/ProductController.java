package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.request.DefineProductToCampaingRequestDTO;
import org.patikadev.orderexample.service.ProductService;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final Validator<CreateProductRequestDTO> createProductValidator;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateProductRequestDTO createProductRequestDTO){
                createProductValidator.validate(createProductRequestDTO);

                productService.create(createProductRequestDTO);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/defineCampaign")
    public ResponseEntity<?> defineProductToCampaign(@RequestBody DefineProductToCampaingRequestDTO defineProductToCampaingRequestDTO){
        productService.defineProductToCampaign(defineProductToCampaingRequestDTO);
        return ResponseEntity.ok().build();
    }







}

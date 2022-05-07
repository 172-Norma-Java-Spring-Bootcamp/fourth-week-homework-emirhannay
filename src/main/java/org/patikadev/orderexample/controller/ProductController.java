package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.request.DefineProductToCampaignRequestDTO;
import org.patikadev.orderexample.dto.response.GetProductsResponseDTO;
import org.patikadev.orderexample.service.ProductService;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity<?> defineProductToCampaign(@RequestBody DefineProductToCampaignRequestDTO defineProductToCampaignRequestDTO){
        productService.defineProductToCampaign(defineProductToCampaignRequestDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Collection<GetProductsResponseDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }








}

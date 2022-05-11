package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.request.DefineProductToCampaignRequestDTO;
import org.patikadev.orderexample.dto.response.GetProductsResponseDTO;
import org.patikadev.orderexample.model.Product;
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

    @PostMapping("/campaigns")
    public ResponseEntity<?> defineProductToCampaign(@RequestBody DefineProductToCampaignRequestDTO defineProductToCampaignRequestDTO){
        productService.defineProductToCampaign(defineProductToCampaignRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<GetProductsResponseDTO>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProductsResponseDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductWithId(id));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete) {
        productService.delete(id,hardDelete);
        return ResponseEntity.ok().build();
    }









}

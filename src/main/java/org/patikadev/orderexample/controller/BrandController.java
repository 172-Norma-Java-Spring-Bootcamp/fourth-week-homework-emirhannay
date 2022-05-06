package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateBrandRequestDTO;
import org.patikadev.orderexample.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/brands")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateBrandRequestDTO createBrandRequestDTO) {
        brandService.create(createBrandRequestDTO);
        return ResponseEntity.ok().build();
    }

}

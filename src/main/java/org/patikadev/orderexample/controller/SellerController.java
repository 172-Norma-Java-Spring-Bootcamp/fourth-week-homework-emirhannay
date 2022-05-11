package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.dto.response.GetSellersResponseDTO;
import org.patikadev.orderexample.service.SellerService;
import org.patikadev.orderexample.validator.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/sellers")
public class SellerController {

    private final SellerService sellerService;
    private final Validator<CreateSellerRequestDTO> createSellerRequestValidator;
    private final Validator<Long> idValidator;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateSellerRequestDTO createSellerRequestDTO){
            //createSellerRequestValidator.validate(createSellerRequestDTO);
            sellerService.create(createSellerRequestDTO);
            return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<GetSellersResponseDTO>> getSellers(){
        return ResponseEntity.ok(sellerService.getSellers());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestParam(name = "hardDelete", required = false) boolean hardDelete){
        idValidator.validate(id);
        sellerService.delete(id,hardDelete);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSellersResponseDTO> getSeller(@PathVariable Long id){
        idValidator.validate(id);
        return ResponseEntity.ok(sellerService.getSellerDtoWithId(id));
    }


}

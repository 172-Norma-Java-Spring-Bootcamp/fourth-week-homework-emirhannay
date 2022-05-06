package org.patikadev.orderexample.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.SellerConverter;
import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Seller;
import org.patikadev.orderexample.repository.SellerRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
@Slf4j
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerConverter sellerConverter;

    @Override
    public void create(CreateSellerRequestDTO createSellerRequestDTO) {
        Seller seller = sellerConverter.toSeller(createSellerRequestDTO);
        sellerRepository.save(seller);
        log.info("Seller created successfully -> {}",seller.getId());
    }

    @Override
    public Seller getSellerWithId(Long id) {
        Seller seller = sellerRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.SellerNotFoundException("Seller not found")
        );
        log.info("Getting seller was successfully -> {} ",seller.getId());
        return seller;
    }

    @Override
    public BigDecimal getSellerShippingCost(Long id) {
        BigDecimal shippingCost = sellerRepository.getSellerShippingCost(id).orElseThrow(
                () -> new BusinessServiceOperationException.SellerNotFoundException("Seller's shipping cost not found")
        );
        log.info("Getting seller shipping cost -> {} ",id);
        return shippingCost;
    }
}

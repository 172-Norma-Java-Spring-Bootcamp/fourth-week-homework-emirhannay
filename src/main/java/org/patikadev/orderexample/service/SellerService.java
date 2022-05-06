package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.model.Seller;

import java.math.BigDecimal;

public interface SellerService {
    void create(CreateSellerRequestDTO createSellerRequestDTO);
    Seller getSellerWithId(Long id);
    BigDecimal getSellerShippingCost(Long id);
}

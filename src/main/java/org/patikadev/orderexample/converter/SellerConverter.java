package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.model.Seller;

public interface SellerConverter {
    Seller toSeller(CreateSellerRequestDTO createSellerRequestDTO);
}

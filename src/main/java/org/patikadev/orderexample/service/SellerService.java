package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.dto.response.GetCustomersResponseDTO;
import org.patikadev.orderexample.dto.response.GetSellersResponseDTO;
import org.patikadev.orderexample.model.Seller;

import java.math.BigDecimal;
import java.util.Collection;

public interface SellerService {
    void create(CreateSellerRequestDTO createSellerRequestDTO);
    void delete(Long id, boolean hardDelete);
    Seller getSellerWithId(Long id);
    BigDecimal getSellerShippingCost(Long id);
    Collection<GetSellersResponseDTO> getSellers();
    GetSellersResponseDTO getSellerDtoWithId(Long id);
}

package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.dto.request.SellerAddressDTO;
import org.patikadev.orderexample.dto.response.GetSellersResponseDTO;
import org.patikadev.orderexample.model.Seller;
import org.patikadev.orderexample.model.SellerAddress;

public interface SellerConverter {
    Seller toSeller(CreateSellerRequestDTO createSellerRequestDTO);
    GetSellersResponseDTO toGetSellersResponse(Seller seller);
    SellerAddressDTO toSellerAddressDTO(SellerAddress sellerAddress);
}

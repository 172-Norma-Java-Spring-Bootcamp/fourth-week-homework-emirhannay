package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.dto.request.SellerAddressDTO;

public record GetSellersResponseDTO(Long id, String name,String username, String email, SellerAddressDTO sellerAddressDTO) {
}

package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.model.Seller;
import org.patikadev.orderexample.model.SellerAddress;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SellerConverterImpl implements SellerConverter{
    @Override
    public Seller toSeller(CreateSellerRequestDTO createSellerRequestDTO) {
        Seller seller = new Seller();
        seller.setUsername(createSellerRequestDTO.userName());
        seller.setEmail(createSellerRequestDTO.email());
        seller.setPassword(createSellerRequestDTO.password());
        seller.setCreatedAt(new Date());
        seller.setCreatedBy(createSellerRequestDTO.createdBy());
        seller.setShippingCost(createSellerRequestDTO.shippingCost());

        SellerAddress sellerAddress = new SellerAddress();
        sellerAddress.setCity(createSellerRequestDTO.sellerAddress().city());
        sellerAddress.setPostalCode(createSellerRequestDTO.sellerAddress().postalCode());
        sellerAddress.setPhoneNumber(createSellerRequestDTO.sellerAddress().phoneNumber());
        sellerAddress.setDescription(createSellerRequestDTO.sellerAddress().description());
        sellerAddress.setCountry(createSellerRequestDTO.sellerAddress().country());
        sellerAddress.setSeller(seller);

        seller.setSellerAddress(sellerAddress);
        return seller;
    }
}

package org.patikadev.orderexample.dto.request;

public record SellerAddressDTO(String phoneNumber,
                               String country,
                               String city,
                               String postalCode,
                               String description) {
}

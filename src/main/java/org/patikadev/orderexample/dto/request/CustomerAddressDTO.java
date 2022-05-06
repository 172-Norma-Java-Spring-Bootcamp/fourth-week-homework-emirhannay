package org.patikadev.orderexample.dto.request;

public record CustomerAddressDTO(String phoneNumber,
                                 String country,
                                 String city,
                                 String postalCode,
                                 String description) {
}

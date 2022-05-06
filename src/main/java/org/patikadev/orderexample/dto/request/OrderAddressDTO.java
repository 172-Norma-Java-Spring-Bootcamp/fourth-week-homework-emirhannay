package org.patikadev.orderexample.dto.request;

public record OrderAddressDTO(String phoneNumber,
                              String country,
                              String city,
                              String postalCode,
                              String description) {
}

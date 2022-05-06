package org.patikadev.orderexample.dto.request;

public record SellerAddress(String phoneNumber,
                            String country,
                            String city,
                            String postalCode,
                            String description) {
}

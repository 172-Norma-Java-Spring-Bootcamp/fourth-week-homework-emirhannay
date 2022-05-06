package org.patikadev.orderexample.dto.request;

public record CreateOrderRequestDTO(OrderAddressDTO orderAddressDTO, Long basketId) {

}

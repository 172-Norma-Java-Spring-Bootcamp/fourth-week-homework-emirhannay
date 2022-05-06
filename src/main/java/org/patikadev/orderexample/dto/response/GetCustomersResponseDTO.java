package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.dto.request.CustomerAddressDTO;
import org.patikadev.orderexample.model.Gender;

public record GetCustomersResponseDTO(Long id,
                                      String username,
                                      String email,
                                      Gender gender,
                                      CustomerAddressDTO customerAddressDTO) {
}

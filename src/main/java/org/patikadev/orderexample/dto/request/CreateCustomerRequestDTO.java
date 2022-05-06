package org.patikadev.orderexample.dto.request;

import org.hibernate.validator.constraints.Length;
import org.patikadev.orderexample.model.Gender;


import javax.validation.constraints.NotNull;

public record CreateCustomerRequestDTO(@NotNull(message = "Created By cant be null")
                                       @Length(min = 2, message = "Created by must be longer than 2 characters") String createdBy,
                                       @NotNull(message = "Username cant be null")
                                       @Length(min = 4, message = "Username must be longer than 2 characters") String userName,
                                       @NotNull(message = "Name cant be null")
                                       @Length(min = 2, message = "Name must be longer than 2 characters") String email,
                                       @Length(min = 11, message = "Identity error", max = 11) Long identity,
                                       Gender gender,
                                       String password,
                                       CustomerAddressDTO customerAddress) {

}

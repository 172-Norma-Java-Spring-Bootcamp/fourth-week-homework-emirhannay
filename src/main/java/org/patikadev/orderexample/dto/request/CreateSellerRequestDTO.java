package org.patikadev.orderexample.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public record CreateSellerRequestDTO(@NotNull(message = "Created By cant be null")
                                     @Length(min = 2, message = "Created by must be longer than 2 characters") String createdBy,

                                     @NotNull(message = "Username cant be null")
                                     @Length(min = 4, message = "Username must be longer than 2 characters") String userName,

                                     @NotNull(message = "Name cant be null")
                                     @Length(min = 2, message = "Name must be longer than 2 characters") String name,

                                     @NotNull(message = "Email cant be null")
                                     @Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", flags = Pattern.Flag.UNICODE_CASE, message = "Email format is not acceptable") String email,

                                     BigDecimal shippingCost,

                                     @Length(min = 6, message = "Password length must be greater than 6", max = 20 ) @NotNull(message = "Password cant be null") String password,

                                     @NotNull(message = "Address cant be null") SellerAddress sellerAddress) {
}

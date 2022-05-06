package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.model.Brand;
import org.patikadev.orderexample.model.Category;
import org.patikadev.orderexample.model.Seller;

import java.math.BigDecimal;

public record GetProductsResponseDTO(String name, String image, BigDecimal price, Category category, Seller seller,
                                     Brand brand, int stock) {
}

package org.patikadev.orderexample.dto.response;

import org.patikadev.orderexample.model.Brand;
import org.patikadev.orderexample.model.Category;
import org.patikadev.orderexample.model.Seller;

import java.math.BigDecimal;
import java.util.UUID;

public record GetProductsResponseDTO(Long id, String name, int stock, UUID barcode, BigDecimal price, CategoryResponseDTO categoryResponseDTO, Seller seller,
                                     Brand brand,String image ) {
}

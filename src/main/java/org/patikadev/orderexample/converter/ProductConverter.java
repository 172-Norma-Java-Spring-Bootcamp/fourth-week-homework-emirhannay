package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.response.GetProductsResponseDTO;
import org.patikadev.orderexample.model.Product;

public interface ProductConverter {

    Product toProduct(CreateProductRequestDTO createProductRequestDTO);
    GetProductsResponseDTO toGetProductsResponse(Product product);

}

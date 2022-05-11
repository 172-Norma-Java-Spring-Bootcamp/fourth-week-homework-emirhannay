package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.request.DefineProductToCampaignRequestDTO;
import org.patikadev.orderexample.dto.response.GetProductsResponseDTO;
import org.patikadev.orderexample.exception.BaseException;
import org.patikadev.orderexample.model.Product;

import java.util.Collection;

public interface ProductService {
    void create(CreateProductRequestDTO createProductRequestDTO);
    Collection<GetProductsResponseDTO> getProducts();
    Collection<Product> getProductsEntity();
    Product getProductEntityWithId(Long id) throws BaseException;
    GetProductsResponseDTO getProductWithId(Long id);
    void defineProductToCampaign(DefineProductToCampaignRequestDTO defineProductToCampaingRequestDTO);
    void delete(Long id, boolean hardDelete) throws BaseException;
}

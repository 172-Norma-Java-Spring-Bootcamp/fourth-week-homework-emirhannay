package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.ProductConverter;
import org.patikadev.orderexample.dto.request.CreateProductRequestDTO;
import org.patikadev.orderexample.dto.request.DefineProductToCampaignRequestDTO;
import org.patikadev.orderexample.dto.response.GetProductsResponseDTO;
import org.patikadev.orderexample.exception.BaseException;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Campaign;
import org.patikadev.orderexample.model.Product;
import org.patikadev.orderexample.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements  ProductService{

    private final ProductConverter productConverter;
    private final ProductRepository productRepository;
    private final CampaignService campaignService;

    @Override
    public void create(CreateProductRequestDTO createProductRequestDTO) {
        Product product = productConverter.toProduct(createProductRequestDTO);
        productRepository.save(product);
        log.info("Product created was successfully -> {}",product.getId());
    }

    public Product getProductEntityWithId(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.ProductNotFoundException("Product not found")
        );
        log.info("Getting product was successfully -> {}",product.getId());
        return product;
    }
    public GetProductsResponseDTO getProductWithId(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.ProductNotFoundException("Product not found")
        );
        log.info("Getting product was successfully -> {}",product.getId());
        return productConverter.toGetProductsResponse(product);
    }

    @Override
    public void defineProductToCampaign(DefineProductToCampaignRequestDTO defineProductToCampaingRequestDTO) {
        Product product = getProductEntityWithId(defineProductToCampaingRequestDTO.productId());
        Campaign campaign = campaignService.getCampaign(defineProductToCampaingRequestDTO.campaignId());
        //Add campaign to product
        product.getCampaigns().add(campaign);
        //Save campaign defined product
        productRepository.save(product);
        log.info("Defining product -> {} to campaign -> {} was successfully ",product.getId(),campaign.getId());
    }

    @Override
    public Collection<GetProductsResponseDTO> getProducts() {
        Collection<GetProductsResponseDTO> productList = productRepository.getProductsByDeleteStatus().orElseThrow(
                () -> new BusinessServiceOperationException.ProductNotFoundException("Products not found")
        )
                .stream()
                .map(productConverter::toGetProductsResponse).toList();
        log.info("Getting products was successfully ");
        return productList;
    }

    @Override
    public Collection<Product> getProductsEntity() {
        Collection<Product>  products = productRepository.getProductsByDeleteStatus().orElseThrow(
                () -> new BusinessServiceOperationException.ProductNotFoundException("Products not found")
        );
        log.info("Getting products was successfully");
        return products;
    }

    @Override
    public void delete(Long id, boolean hardDelete) throws BaseException {
    Product product =  getProductEntityWithId(id);
    if(hardDelete){
            productRepository.delete(product);
            log.info("Hard delete product was successfully -> {}",product.getId());
            return;
    }
    if(product.isDeleted()){
        log.info("Product -> {} already deleted",product.getId());
        throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Product already deleted");
    }
    product.setDeleted(true);
    productRepository.save(product);
    log.info("Delete product was successfully -> {}",product.getId());
    }
}

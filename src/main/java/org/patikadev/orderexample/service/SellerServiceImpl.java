package org.patikadev.orderexample.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.SellerConverter;
import org.patikadev.orderexample.dto.request.CreateSellerRequestDTO;
import org.patikadev.orderexample.dto.response.GetSellersResponseDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Seller;
import org.patikadev.orderexample.repository.SellerRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;

@RequiredArgsConstructor
@Component
@Slf4j
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerConverter sellerConverter;

    @Override
    public void create(CreateSellerRequestDTO createSellerRequestDTO) {
        Seller seller = sellerConverter.toSeller(createSellerRequestDTO);
        sellerRepository.save(seller);
        log.info("Seller created successfully -> {}",seller.getId());
    }

    @Override
    public void delete(Long id, boolean hardDelete) {
        Seller seller =  getSellerWithId(id);
        if(hardDelete){
            sellerRepository.delete(seller);
            log.info("Hard delete seller was successfully -> {}",seller.getId());
            return;
        }
        if(seller.isDeleted()){
            log.info("Seller -> {} already deleted",seller.getId());
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Product already deleted");
        }
        seller.setDeleted(true);
        sellerRepository.save(seller);
        log.info("Delete seller was successfully -> {}",seller.getId());
    }


    @Override
    public Seller getSellerWithId(Long id) {
        Seller seller = sellerRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.SellerNotFoundException("Seller not found")
        );
        log.info("Getting seller was successfully -> {} ",seller.getId());
        return seller;
    }
    public GetSellersResponseDTO getSellerDtoWithId(Long id) {
        Seller seller = sellerRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.SellerNotFoundException("Seller not found")
        );
        log.info("Getting seller was successfully -> {} ",seller.getId());
        return sellerConverter.toGetSellersResponse(seller);
    }

    @Override
    public BigDecimal getSellerShippingCost(Long id) {
        BigDecimal shippingCost = sellerRepository.getSellerShippingCost(id).orElseThrow(
                () -> new BusinessServiceOperationException.SellerNotFoundException("Seller's shipping cost not found")
        );
        log.info("Getting seller shipping cost -> {} ",id);
        return shippingCost;
    }

    @Override
    public Collection<GetSellersResponseDTO> getSellers() {
        Collection<GetSellersResponseDTO> sellers = sellerRepository.getAllSellersByDeleteStatus(false).orElseThrow(
                ()-> new BusinessServiceOperationException.SellerNotFoundException("Sellers not found")
        )
                .stream()
                .map(sellerConverter::toGetSellersResponse).toList();
        log.info("Getting sellers was successfully ");
        return sellers;
    }
}

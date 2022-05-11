package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "select shipping_cost from seller s where s.seller_id= :id ", nativeQuery = true)
    Optional<BigDecimal> getSellerShippingCost(@Param("id") Long id);

    @Query(value = "SELECT * FROM seller s WHERE s.isDeleted = :status",nativeQuery = true)
    Optional<Collection<Seller>> getAllSellersByDeleteStatus(@Param("status") boolean status);
}

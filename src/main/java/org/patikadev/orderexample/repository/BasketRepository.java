package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query(value = "select * from basket AS b where b.customer_id= :customerId ", nativeQuery = true)
    Optional<List<Basket>> getBasketByCustomerId(@Param("customerId") Long customerId);
    @Query(value = "select * from basket AS b where b.customer_id= :basketId ", nativeQuery = true)
    Optional<Basket> getBasketByBasketId(@Param("basketId") Long basketId);


}

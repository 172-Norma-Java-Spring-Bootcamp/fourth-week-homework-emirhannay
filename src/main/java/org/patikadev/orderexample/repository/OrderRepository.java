package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {


    Order findByBasketId(Long basketId);
    Optional<Order> findByCustomerId(Long customerId);
}

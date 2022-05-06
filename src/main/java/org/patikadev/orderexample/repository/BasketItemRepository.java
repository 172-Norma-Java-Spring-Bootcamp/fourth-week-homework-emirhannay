package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem,Long> {
}

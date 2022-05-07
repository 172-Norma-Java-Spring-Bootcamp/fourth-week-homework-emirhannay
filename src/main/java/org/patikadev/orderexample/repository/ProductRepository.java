package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
        @Query(value = "select * from seller s where s.is_deleted= false ", nativeQuery = true)
        Optional<Collection<Product>> getProductsByDeleteStatus();
}

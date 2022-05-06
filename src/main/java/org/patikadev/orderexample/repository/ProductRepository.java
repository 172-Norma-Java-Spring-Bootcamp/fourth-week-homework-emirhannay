package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

        Optional<Collection<Product>> getProducts();
}

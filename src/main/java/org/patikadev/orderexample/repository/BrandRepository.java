package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}

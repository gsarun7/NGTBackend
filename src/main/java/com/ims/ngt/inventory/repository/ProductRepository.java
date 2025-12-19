package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameIgnoreCase(String name);

    List<Product> findByCategoryId(Long categoryId);
}

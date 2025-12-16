package com.ims.ngt.inventory.repository;

import com.ims.ngt.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

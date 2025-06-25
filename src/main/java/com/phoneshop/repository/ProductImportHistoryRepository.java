package com.phoneshop.repository;

import com.phoneshop.model.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long> {
}

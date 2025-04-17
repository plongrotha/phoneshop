package com.phoneshop.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phoneshop.org.model.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
  List<Brand> findByBrandNameContaining(String name);
}

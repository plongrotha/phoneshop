package com.phoneshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.phoneshop.model.entity.Brand;

import javax.swing.text.html.Option;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, JpaSpecificationExecutor<Brand>{
//  List<Brand> findByBrandNameLike(String name);
//  List<Brand> findByBrandNameContaining(String name);
  Optional<Brand> findBrandByBrandName(String brandName);
}

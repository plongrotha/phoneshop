package com.phoneshop.repository;

import com.phoneshop.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByBrand_BrandId(Long brandId);

}

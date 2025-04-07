package com.phoneshop.org.repository;

import com.phoneshop.org.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Long, Brand> {


}

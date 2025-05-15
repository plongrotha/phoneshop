package com.phoneshop.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.phoneshop.model.entity.Brand;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BrandSpecification implements Specification<Brand> {

  private final BrandFilter brandFilter;
  List<Predicate> predicates = new ArrayList<>();

  @Override
  @Nullable
  public Predicate toPredicate(Root<Brand> brand, @Nullable CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

    if (brandFilter == null) {
      return null;
    }

    if(brandFilter.getBrandName() != null) {
      //  Predicate name = brand.get("name").in(brandFilter.getName());
     Predicate name = criteriaBuilder.like(brand.get("brandname"),"%" + brandFilter.getBrandName() + "%");
      predicates.add(name);
    }

    if(brandFilter.getId() != null) {
      Predicate id = brand.get("id").in(brandFilter.getId());
      predicates.add(id);
    }

    // return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    return criteriaBuilder.and(predicates.toArray(Predicate[]::new)); // use constructor reference
  }


}

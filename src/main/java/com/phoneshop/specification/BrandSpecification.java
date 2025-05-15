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
  public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

    if(brandFilter.getName() != null){
      Predicate name =  brand.get("name").in(brandFilter.getName());
      predicates.add(name);
    }

    if(brandFilter.getId() != null){
      Predicate id = brand.get("id").in(brandFilter.getId());
      predicates.add(id);
    }
//     predicates.toArray(new Predicate[0]);
    return criteriaBuilder.and(predicates.toArray(Predicate[]::new));

  }

}

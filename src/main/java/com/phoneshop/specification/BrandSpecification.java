package com.phoneshop.specification;

import com.phoneshop.model.entity.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BrandSpecification implements Specification<Brand> {

    private final BrandFilter brandFilter;

    List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if(brandFilter.getName() != null){
            Predicate name = brand.get("name").in(brandFilter.getName());
            predicates.add(name);
        }

        if(brandFilter.getId() != null){
            Predicate id = brand.get("id").in(brandFilter.getId());
            predicates.add(id);
        }

        // convert Predication to array
        Predicate[] predicateArray = predicates.toArray(Predicate[]::new);

        return criteriaBuilder.and(predicateArray);
    }
}

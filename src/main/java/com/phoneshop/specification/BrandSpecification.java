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

    @Override
    public Predicate toPredicate(Root<Brand> brand, CriteriaQuery<?> query, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

//        if(brandFilter.getBrandName() != null){
//            Predicate name = brand.get("name").in(brandFilter.getBrandName());
//            predicates.add(name);
//        }
//
//        if(brandFilter.getBrandId() != null){
//            Predicate id = brand.get("id").in(brandFilter.getBrandId());
//            predicates.add(id);
//        }


        if (brandFilter.getBrandName() != null) {
            predicates.add(cb.like(cb.lower(brand.get("brandName")), "%" + brandFilter.getBrandName().toLowerCase() + "%"));
        }

        if (brandFilter.getBrandId() != null) {
            predicates.add(cb.equal(brand.get("brandId"), brandFilter.getBrandId()));
        }


        // convert Predication to array
        Predicate[] predicateArray = predicates.toArray(Predicate[]::new);

        return cb.and(predicateArray);
    }
}

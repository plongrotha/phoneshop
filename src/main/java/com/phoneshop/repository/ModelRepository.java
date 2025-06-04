package com.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phoneshop.model.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Integer>{

}

package com.phoneshop.repository;

import com.phoneshop.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer>{

}

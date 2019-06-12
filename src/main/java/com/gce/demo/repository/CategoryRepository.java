package com.gce.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gce.demo.bo.CategoryInfo;


public interface CategoryRepository extends JpaRepository<CategoryInfo,Long> {

}

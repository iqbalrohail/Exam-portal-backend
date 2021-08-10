package com.examportal.examportalapi.repository;

import com.examportal.examportalapi.domain.CategoryDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDomain , Integer> {
}

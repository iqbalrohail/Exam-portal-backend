package com.examportal.examportalapi.repository;

import com.examportal.examportalapi.domain.QuestionDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionDomain , Integer> {
}

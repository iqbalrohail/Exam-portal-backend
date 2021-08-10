package com.examportal.examportalapi.repository;

import com.examportal.examportalapi.domain.QuizDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizDomain , Integer> {
}

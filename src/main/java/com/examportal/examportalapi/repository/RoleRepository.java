package com.examportal.examportalapi.repository;

import com.examportal.examportalapi.domain.RoleDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDomain , Integer> {
}

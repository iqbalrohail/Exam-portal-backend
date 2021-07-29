package com.examportal.examportalapi.repository;

import com.examportal.examportalapi.domain.UserRoleDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleDomain , Integer> {
}

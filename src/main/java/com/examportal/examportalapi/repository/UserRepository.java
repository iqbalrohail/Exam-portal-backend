package com.examportal.examportalapi.repository;

import com.examportal.examportalapi.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDomain , Integer> {

    UserDomain findByUsername(String name);
}

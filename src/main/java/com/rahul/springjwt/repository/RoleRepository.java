package com.rahul.springjwt.repository;

import com.rahul.springjwt.model.dao.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleDao, Long> {
}

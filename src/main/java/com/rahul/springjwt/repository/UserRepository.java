package com.rahul.springjwt.repository;

import com.rahul.springjwt.model.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDao, Long> {

    boolean existsByUsername(String username);
    Optional<UserDao> findByUsername(String username);
}

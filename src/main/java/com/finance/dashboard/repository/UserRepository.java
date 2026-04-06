package com.finance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.dashboard.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}

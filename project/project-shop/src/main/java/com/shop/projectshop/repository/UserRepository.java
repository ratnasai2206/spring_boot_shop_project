package com.shop.projectshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.projectshop.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

}

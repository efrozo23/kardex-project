package com.shop.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.kardex.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT  * FROM  `user` u where email = :email limit 1  ",nativeQuery = true)
	User findByEmail(@Param("email") String email);
}

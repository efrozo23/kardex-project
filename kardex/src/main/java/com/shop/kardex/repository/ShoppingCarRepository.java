package com.shop.kardex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.kardex.entity.ShoppingCar;

public interface ShoppingCarRepository extends JpaRepository<ShoppingCar,Long>{
	
	@Query("Select s From ShoppingCar s where s.user.email = :email")
	List<ShoppingCar> findByUserEmail(@Param("email") String email);

}

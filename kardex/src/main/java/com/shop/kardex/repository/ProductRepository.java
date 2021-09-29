package com.shop.kardex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.kardex.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

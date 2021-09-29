package com.shop.kardex.dao;

import java.util.List;

import com.shop.kardex.dto.ShoppingCarDTO;

public interface ShoppingCarDAO extends CrudService<ShoppingCarDTO>{
	
	List<ShoppingCarDTO> getItemsByEmail(String email);
}

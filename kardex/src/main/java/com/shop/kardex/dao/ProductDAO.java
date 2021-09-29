package com.shop.kardex.dao;

import java.util.List;

import com.shop.kardex.dto.ProductDTO;

public interface ProductDAO extends CrudService<ProductDTO>{
	
	boolean containsStock(Long idProduct,Integer quantity);
	
	List<ProductDTO> getAllItems();

}

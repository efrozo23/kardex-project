package com.shop.kardex.dao;

import com.shop.kardex.dto.UserDTO;


public interface UserDAO extends CrudService<UserDTO>{
	
	boolean userExists(String email);

}

package com.shop.kardex.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.kardex.dao.UserDAO;
import com.shop.kardex.dto.UserDTO;

@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserDAO userDao;
	
	@BeforeEach()
	public  void saveUser() {
		UserDTO userDto = new UserDTO();
		userDto.setName("Elkin");
		userDto.setLastName("Rozo");
		userDto.setPassword("123");
		userDto.setEmail("e@t.com");
		userDao.save(userDto);
	}
	
	@Test
	public void whenValidUserthenSave() {
		UserDTO userDto = new UserDTO();
		userDto.setName("Elkin");
		userDto.setLastName("Rozo");
		userDto.setPassword("123");
		userDto.setEmail("prueba@t.com");
		userDao.save(userDto);
	}
	
	@Test
	public void whenUserNotExistThenFalse() {
		boolean r = this.userDao.userExists("test@t.com");
		assertEquals(false, r);
	}
	
	@Test 
	public void whenUserExistThenTrue() {
		boolean r = this.userDao.userExists("e@t.com");
		assertEquals(true, r);
	}
}

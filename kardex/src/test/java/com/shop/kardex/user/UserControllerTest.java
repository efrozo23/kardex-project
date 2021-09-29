package com.shop.kardex.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.shop.kardex.dao.UserDAO;
import com.shop.kardex.dto.UserDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest {

	@Value("${server.port}")
	private String serverPort;

	private final String URL = "http://localhost:";

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private UserDAO userDao;

	@Test
	public void whenSaveUserThenOK() {
		

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		UserDTO u = new UserDTO();
		u.setName("Elkin");
		u.setLastName("Rozo");
		u.setPassword("123");
		u.setEmail("prueba@t.com");
		
		HttpEntity<?> entity = new HttpEntity<UserDTO>(u, httpHeaders);
		ResponseEntity<?> responseEntity = restTemplate.exchange(URL + serverPort + "/user/save",
				HttpMethod.POST, entity, String.class);
		assertEquals(responseEntity.getStatusCodeValue(), 201);
		
	}
	
	@Test
	public void whenUserExistThenBadRequest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		UserDTO u = new UserDTO();
		u.setEmail("prueba@t.com");
		Mockito.when(this.userDao.userExists("prueba@t.com")).thenReturn(true);
		HttpEntity<?> entity = new HttpEntity<UserDTO>(u, httpHeaders);
		ResponseEntity<?> responseEntity = restTemplate.exchange(URL + serverPort + "/user/save",
				HttpMethod.POST, entity, String.class);
		assertEquals(responseEntity.getStatusCodeValue(), 400);
	}

}

package com.shop.kardex.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.shop.kardex.dao.ProductDAO;
import com.shop.kardex.dto.ProductDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ProductControllerTest {
	
	@LocalServerPort
	private String serverPort;

	private final String URL = "http://localhost:";

	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private ProductDAO productDAO;
	
	@Test
	public void whenSaveProductThenOK() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ProductDTO p = new ProductDTO();
		
		HttpEntity<?> entity = new HttpEntity<>(p, httpHeaders);
		ResponseEntity<?> responseEntity = restTemplate.exchange(URL + serverPort + "/product/save",
				HttpMethod.POST, entity, String.class);
		assertEquals(responseEntity.getStatusCodeValue(), 201);
		
	}
	
	@Test
	public void whenAllItemsThenOK() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ProductDTO p = new ProductDTO();
		List<ProductDTO> listItems = new ArrayList<ProductDTO>();
		listItems.add(p);
		Mockito.when(this.productDAO.getAllItems()).thenReturn(listItems);
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(URL + serverPort + "/product/all", List.class);
		assertEquals(responseEntity.getBody().size(), 1);
	}
}

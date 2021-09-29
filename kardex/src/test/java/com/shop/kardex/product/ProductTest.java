package com.shop.kardex.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shop.kardex.dao.ProductDAO;
import com.shop.kardex.dto.ProductDTO;
import com.shop.kardex.entity.Product;
import com.shop.kardex.entity.TypeProduct;
import com.shop.kardex.repository.ProductRepository;
import com.shop.kardex.repository.TypeProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {
	
	@Autowired
	private ProductDAO productDAO;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Autowired 
	private TypeProductRepository typeProductRepository;
	
	@BeforeEach
	public void configCategory() {
		TypeProduct typeProduct = new TypeProduct();
		typeProduct.setId(1l);
		typeProduct.setName("DC");
		this.typeProductRepository.save(typeProduct);
	}
	
	@Test
	public void whenSaveProductThenOK() {
		ProductDTO  p = new ProductDTO();
		p.setName("product1");
		p.setTypeProduct(1l);
		productDAO.save(p);
	}
	
	@Test
	public void whenDoesNotHaveStockThenFalse() {
		Product p = new Product();
		p.setStock(10);
		Optional<Product> optional = Optional.of(p);
		Mockito.when(this.productRepository.findById(1l)).thenReturn(optional);
		assertEquals(this.productDAO.containsStock(1l,11), false);
	}
	
	@Test
	public void whenDoesNotHaveStockThenTrue() {
		Product p = new Product();
		p.setStock(10);
		Optional<Product> optional = Optional.of(p);
		Mockito.when(this.productRepository.findById(1l)).thenReturn(optional);
		assertEquals(this.productDAO.containsStock(1l,8), true);
	}

}

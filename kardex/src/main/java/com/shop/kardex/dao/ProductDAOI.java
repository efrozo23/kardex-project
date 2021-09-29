package com.shop.kardex.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.shop.kardex.dto.ProductDTO;
import com.shop.kardex.entity.Product;
import com.shop.kardex.entity.TypeProduct;
import com.shop.kardex.repository.ProductRepository;
import com.shop.kardex.repository.TypeProductRepository;

@Repository
public class ProductDAOI implements ProductDAO{
	
	private ProductRepository productRepository;
	
	private TypeProductRepository typeProductRepository;
	
	public ProductDAOI(ProductRepository productRepository, TypeProductRepository typeProductRepository) {
		this.productRepository = productRepository;
		this.typeProductRepository = typeProductRepository;
	}



	@Override
	public void save(ProductDTO dto) {
		Optional<TypeProduct> typeProduct = this.typeProductRepository.findById(dto.getTypeProduct());
		Product p = new Product();
		p.setName(dto.getName());
		p.setDescription(dto.getDescription());
		p.setPrice(dto.getPrice());
		p.setStock(dto.getStock());
		p.setTypeProduct(typeProduct.get());
		p.setCreationDate(LocalDateTime.now().toString());
		productRepository.save(p);
	}



	@Override
	public List<ProductDTO> getAllItems() {
		List<Product> listItems = this.productRepository.findAll();
		List<ProductDTO> listItemsDTO = new ArrayList<ProductDTO>();
		listItems.forEach(item -> {
			ProductDTO p = new ProductDTO();
			p.setId(item.getId());
			p.setName(item.getName());
			p.setDescription(item.getDescription());
			p.setPrice(item.getPrice());
			p.setCreationDate(item.getCreationDate());
			p.setStock(item.getStock());
			p.setTypeProduct(item.getTypeProduct().getId());
			listItemsDTO.add(p);
		});
		return listItemsDTO;
	}



	@Override
	public boolean containsStock(Long idProduct,Integer quantity) {
		Optional<Product> product = this.productRepository.findById(idProduct);
		return product.get().getStock() >= quantity;
	}
	

}

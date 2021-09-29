package com.shop.kardex.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.shop.kardex.dao.ShoppingCarDAO;
import com.shop.kardex.dto.ProductDTO;
import com.shop.kardex.dto.ShoppingCarDTO;
import com.shop.kardex.dto.UserDTO;
import com.shop.kardex.entity.Product;
import com.shop.kardex.entity.ShoppingCar;
import com.shop.kardex.entity.User;
import com.shop.kardex.repository.ProductRepository;
import com.shop.kardex.repository.ShoppingCarRepository;
import com.shop.kardex.repository.UserRepository;

@Repository
public class ShoppingCarDAOI implements ShoppingCarDAO {
	
	private UserRepository userRepository;
	private ProductRepository productRepository;
	private ShoppingCarRepository shoppingCarRepository;

	public ShoppingCarDAOI(UserRepository userRepository, ProductRepository productRepository,
			ShoppingCarRepository shoppingCarRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.shoppingCarRepository = shoppingCarRepository;
	}

	@Override
	public void save(ShoppingCarDTO dto) {
		
		Optional<Product> product = this.productRepository.findById(dto.getProductDTO().getId());
		User user = this.userRepository.findByEmail(dto.getUserDTO().getEmail());
		ShoppingCar shoppingCar = new ShoppingCar();
		shoppingCar.setUser(user);
		shoppingCar.setProduct(product.get());
		shoppingCar.setQuantity(dto.getQuantity());
		this.shoppingCarRepository.save(shoppingCar);
		product.get().setStock(product.get().getStock() - dto.getQuantity());
		this.productRepository.save(product.get());
	}

	

	@Override
	public List<ShoppingCarDTO> getItemsByEmail(String email) {
		List<ShoppingCar> listItems = this.shoppingCarRepository.findByUserEmail(email);
		List<ShoppingCarDTO> listItemsDTO = new ArrayList<>();
		listItems.forEach(item ->{
			ShoppingCarDTO dto = new ShoppingCarDTO();
			ProductDTO p = new ProductDTO();
			UserDTO u = new UserDTO();
			p.setPrice(item.getProduct().getPrice());
			p.setName(item.getProduct().getName());
			p.setDescription(item.getProduct().getDescription());
			u.setEmail(item.getUser().getEmail());
			dto.setQuantity(item.getQuantity());
			dto.setProductDTO(p);
			dto.setUserDTO(u);
			listItemsDTO.add(dto);
		});
		return listItemsDTO;
	}

	

}

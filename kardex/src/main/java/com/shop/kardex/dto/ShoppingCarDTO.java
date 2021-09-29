package com.shop.kardex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCarDTO {
	
	@JsonProperty("user")
	private UserDTO userDTO;
	
	@JsonProperty("product")
	private ProductDTO productDTO;
	
	@JsonProperty("quantity")
	private Integer quantity;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}

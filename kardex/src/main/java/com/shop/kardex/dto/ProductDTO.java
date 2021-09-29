package com.shop.kardex.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class ProductDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	@JsonProperty
	private String name;

	private String description;

	@NotNull
	private Double price;

	private String creationDate;

	@NotNull
	private Integer stock;

	private Long typeProduct;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(Long typeProduct) {
		this.typeProduct = typeProduct;
	}

	public Long getId() {
		return id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}

package com.shop.kardex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class TypeProduct {

	public TypeProduct() {
	}

	public TypeProduct(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id_category")
	private Long id;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

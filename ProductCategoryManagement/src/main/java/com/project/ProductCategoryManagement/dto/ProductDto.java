package com.project.ProductCategoryManagement.dto;

import com.project.ProductCategoryManagement.Entity.Category;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

public class ProductDto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;

	private String productName;

	private String description;
	
	@Min(value=0,message="Minimum price should be >=0")
	private double price;
	
	@Min(value=0,message="Minimum quantity should be >=0")
	private Integer quantity;
	
	@ManyToOne(fetch= FetchType.LAZY) // in order to fetch only when we want
	@JoinColumn(name="catgeory_id")
	private Category category;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}

package com.project.ProductCategoryManagement.dto;

import java.util.List;

import com.project.ProductCategoryManagement.Entity.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

public class CategoryDto {

	private long categoryId;

	@NotBlank(message="Category name is required")
	private String categoryName;

	private String description;

	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private List<Product>productlist;

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;	

	}

}

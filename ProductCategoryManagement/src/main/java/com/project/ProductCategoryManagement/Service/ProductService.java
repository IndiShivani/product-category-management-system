package com.project.ProductCategoryManagement.Service;
import java.util.List;

import com.project.ProductCategoryManagement.dto.CategoryDto;
import com.project.ProductCategoryManagement.dto.ProductDto;

public interface ProductService{

	ProductDto create(ProductDto dto,Long categoryId);
	ProductDto update(ProductDto dto, Long productId);
	List<ProductDto> getAll(String sortBy);
	ProductDto getById(Long id);
	String delete(CategoryDto dto, Long id);
}

package com.project.ProductCategoryManagement.Service;

import java.util.List;

import com.project.ProductCategoryManagement.dto.CategoryDto;

import jakarta.validation.Valid;

public interface CategoryService {

	CategoryDto create(CategoryDto dto);

	CategoryDto update(@Valid CategoryDto dto,Long id);

	String deleteAll();

	List<CategoryDto> saveAll(List<CategoryDto> categoryList);

	List<CategoryDto> getAll(String sortBy);

	String delete(Long id);
	
	}

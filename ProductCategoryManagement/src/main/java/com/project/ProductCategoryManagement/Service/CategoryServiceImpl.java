package com.project.ProductCategoryManagement.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ProductCategoryManagement.Entity.Category;
import com.project.ProductCategoryManagement.Repository.CategoryRepo;
import com.project.ProductCategoryManagement.dto.CategoryDto;
import com.project.ProductCategoryManagement.exception.EmptyListException;
import com.project.ProductCategoryManagement.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo repo;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public CategoryDto create(CategoryDto dto) { // saving
		Category category=modelMapper.map(dto,Category.class);//dto-entity
		Category savedCategory=repo.save(category); //saving
		CategoryDto retCategory=
				modelMapper.map(savedCategory,CategoryDto.class);//converting back to Dto
		return retCategory ;
	}

	@Override
	public CategoryDto update(CategoryDto dto, Long id) { //updating
		Category category=repo.findById(id).
				orElseThrow(() -> new ResourceNotFoundException ("category doesn't exists."));
		
		category.setCategoryName(dto.getCategoryName());
		category.setDescription(dto.getDescription());
		
		Category savedCategory=repo.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class) ;
	}

	@Override
	public String delete(Long id) { // delete
		Category categoryNew = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found"));
		repo.delete(categoryNew);
		return "deleted succefully";
	}

	@Override
	public String deleteAll() {
		repo.deleteAll();
		return "All categories are deleted successfully";
	}

	@Override
	public List<CategoryDto> saveAll(List<CategoryDto> categoryList) {
		
		if(categoryList.isEmpty()) {
			throw new EmptyListException("List is Empty");
		}
		
		List<Category>entityList=
		categoryList
		.stream()
		.map((category) -> modelMapper.map(category, Category.class))
		.collect(Collectors.toList());
	 
		List<Category>savedCategory=repo.saveAll(entityList);
		
		return savedCategory
				.stream()
				.map((category) -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList()); 
	}

	@Override
	public List<CategoryDto> getAll(String sortBy) {
		String sortingParameter=
				(sortBy !=null && !sortBy.isBlank() ? sortBy :"categoryId");
		
		List<Category>sortedCategory= repo.findAll(Sort.by(sortingParameter).ascending()); 
		
		return sortedCategory
				.stream()
				.map((category) -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}
	
	
}

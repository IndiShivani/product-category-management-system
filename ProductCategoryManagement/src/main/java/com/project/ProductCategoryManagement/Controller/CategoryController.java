package com.project.ProductCategoryManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ProductCategoryManagement.Service.CategoryService;
import com.project.ProductCategoryManagement.dto.CategoryDto;

import jakarta.validation.Valid;

@RestController// combination of controller and response body
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	CategoryService service;
	
	@PostMapping("/create")  // exception handling 
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto dto) {
	return new ResponseEntity<CategoryDto>
	(service.create(dto),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto dto, @PathVariable Long id ) {
		return new ResponseEntity <CategoryDto> 
		(service.update(dto, id),HttpStatus.OK);
	}
	
	/*
	 * @DeleteMapping("/delete/{id}") public String ResponseEntity
	 * delete(@PathVariable Long id) { service.delete( id); return new
	 * ResponseEntity(HttpStatus.NO_CONTENT); }
	 */
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	 
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		return service.deleteAll();
	}
	
	@PostMapping("/saveAll")
	public List<CategoryDto> saveAll(
			@RequestBody List<CategoryDto> CategoryList){
		return service.saveAll(CategoryList);
	}
	
	@GetMapping
	public List<CategoryDto>getAll(
			@RequestParam(required=false)String sortBy){
		return service.getAll(sortBy);
	}
}

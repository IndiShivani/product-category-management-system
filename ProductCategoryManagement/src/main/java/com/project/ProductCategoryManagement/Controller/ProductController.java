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
import com.project.ProductCategoryManagement.Service.ProductService;
import com.project.ProductCategoryManagement.dto.CategoryDto;
import com.project.ProductCategoryManagement.dto.ProductDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	public ProductService service;
	
	@PostMapping("/create/{categoryId}")
	public ProductDto create(@Valid @RequestBody ProductDto dto,
			@PathVariable Long categoryId) {
		return service.create(dto, categoryId);
	}
	
	@PutMapping("/update/{id}")
	public ProductDto update(@RequestBody ProductDto dto, @PathVariable Long id) {
		return service.update(dto, id);
	}
	
	@GetMapping("/get")
	public List<ProductDto>getAll(
			@RequestParam(required=false)String sortBy){
		return service.getAll(sortBy);
	}
	
	@GetMapping("/getById/{id}")
	public ProductDto getById(@PathVariable Long id) {
	    return service.getById(id);
	}
	
	@DeleteMapping("/delete/{id}") 
	  public String delete(@RequestBody CategoryDto dto,@PathVariable Long id) {
	   return service.delete(dto, id);
	  }
}

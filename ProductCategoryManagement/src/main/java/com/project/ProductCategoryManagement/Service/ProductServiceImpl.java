package com.project.ProductCategoryManagement.Service;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.ProductCategoryManagement.Entity.Category;
import com.project.ProductCategoryManagement.Entity.Product;
import com.project.ProductCategoryManagement.Repository.CategoryRepo;
import com.project.ProductCategoryManagement.Repository.ProductRepo;
import com.project.ProductCategoryManagement.dto.CategoryDto;
import com.project.ProductCategoryManagement.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo Productrepo ;

	@Autowired
	CategoryRepo Categoryrepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public ProductDto create(ProductDto dto,Long categoryId) {  // create
		Category category = Categoryrepo.findById(categoryId).
				orElseThrow(() -> new RuntimeException("product doesn't exsits"));
		Product product=modelMapper.map(dto,Product.class);
		product.setCategory(category);
		Product savedProduct =Productrepo.save(product);
		ProductDto retProduct = modelMapper.map(savedProduct, ProductDto.class);
		return retProduct;
	}

	@Override
	public ProductDto update(ProductDto dto, Long productId) {   // update
		Product product=Productrepo.findById(productId).
				orElseThrow(() -> new RuntimeException ("product doesn't exists."));

		product.setProductName(dto.getProductName());
		product.setDescription(dto.getDescription());

		Product savedProduct=Productrepo.save(product);
		return modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAll(String sortBy) { // get all
			String sortingParameter=
					(sortBy !=null && !sortBy.isBlank() ? sortBy :"productId");
			
			List<Product>sortedProduct= Productrepo.findAll(Sort.by(sortingParameter).ascending()); 
			
			return sortedProduct
					.stream()
					.map((product) -> modelMapper.map(product, ProductDto.class))
					.collect(Collectors.toList());
	}

	@Override
	public ProductDto getById(Long id) {       // get by id
	    Product product = Productrepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

	    ProductDto dto = new ProductDto();
	    dto.setProductId(product.getProductId());
	    dto.setProductName(product.getProductName());
	    dto.setPrice(product.getPrice());
	    dto.setDescription(product.getDescription());
	    dto.setQuantity(product.getQuantity());
	    dto.setCategory(product.getCategory());
	    return dto;
	}

	@Override
	public String delete(CategoryDto dto, Long id) { // delete
			 Product productNew=Productrepo.findById(id).
					 orElseThrow(() -> new RuntimeException("id not found"));
			 Productrepo.delete(productNew);
			return "deleted succefully";
		}
	}

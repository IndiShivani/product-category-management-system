package com.project.ProductCategoryManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.ProductCategoryManagement.Entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

}

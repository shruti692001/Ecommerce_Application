package com.ecommerce.project.service;

import com.ecommerce.project.module.Category;
import com.ecommerce.project.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

     @Autowired
    CategoryRepo  categoryRepo;

     public void createCategory(Category category){
          categoryRepo.save(category);
     }

     public List<Category> listCategory(){
         return categoryRepo.findAll();
     }
     public void editCatgory(int categoryID,Category updatecategory) {
         Category category = categoryRepo.getById(categoryID);
         category.setCategoryName(updatecategory.getCategoryName());
         category.setDescription(updatecategory.getDescription());
         category.setImageUrl(updatecategory.getImageUrl());
         categoryRepo.save(category);
     }

     public boolean findById(int categoryId){
         return categoryRepo.findById(categoryId).isPresent();
     }
}


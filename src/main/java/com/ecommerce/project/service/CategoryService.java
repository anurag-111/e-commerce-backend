package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addNewCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if(existingCategory.isPresent()) {
            throw new IllegalArgumentException("Category with this name already exists.");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        if(!categoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Category with ID: " + categoryId + " not found!");
        }
        categoryRepository.deleteById(categoryId);
    }

    public String findCategoryByName(String categoryName) {
        Optional<Category> categoryOptional = categoryRepository.findByCategoryName(categoryName);
        if(categoryOptional.isPresent()) {
            Category foundCategory = categoryOptional.get();
            return foundCategory.getCategoryName();
        } else {
            return "Category with name" + categoryName + " does not exists.";
        }
    }

    public void updateCategory(Category updatedCategory, Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();

            // Update existing details
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category with ID: " + categoryId + " not found!");
        }
    }
}
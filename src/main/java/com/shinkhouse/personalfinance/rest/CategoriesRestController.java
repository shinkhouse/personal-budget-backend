package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.CategoryModel;
import com.shinkhouse.personalfinance.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoriesRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    // POST: Create a new category
    @PostMapping
    public CategoryModel createCategory(@RequestBody CategoryModel category) {
        return categoryRepository.save(category);
    }

    // PUT: Update an existing category by ID
    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable Integer id,
            @RequestBody CategoryModel updatedCategory) {
        Optional<CategoryModel> categoryData = categoryRepository.findById(id);

        if (categoryData.isPresent()) {
            CategoryModel category = categoryData.get();
            category.setDescription(updatedCategory.getDescription());
            category.setIsDefault(updatedCategory.getIsDefault());
            category.setType(updatedCategory.getType());
            category.setCreatedOn(updatedCategory.getCreatedOn());
            category.setCreatedBy(updatedCategory.getCreatedBy());
            category.setUpdatedOn(updatedCategory.getUpdatedOn());
            category.setUpdatedBy(updatedCategory.getUpdatedBy());
            return ResponseEntity.ok(categoryRepository.save(category));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

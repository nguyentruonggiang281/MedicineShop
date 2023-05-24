package com.shop.medicineshop.service;

import com.shop.medicineshop.model.category.Category;
import com.shop.medicineshop.repository.CategoryRepository;
import com.shop.medicineshop.response.category.CategoryDTO;
import com.shop.medicineshop.response.category.CategoryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
@Autowired
private CategoryDTOMapper categoryDTOMapper;
    @Transactional
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryDTOMapper.mapCategoriesToDTO(categoryRepository.findAll());
    }

    public CategoryDTO getCategoryById(Integer id) {
        return categoryDTOMapper.apply(categoryRepository.getCategoriesById(id));
    }

    public void updateCategory(Integer id, Category product) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(product.getName());
            existingCategory.setDescription(product.getDescription());

            categoryRepository.save(existingCategory);
        }

    }

    @Transactional
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> getSubcategories(Integer id) {
        return categoryDTOMapper.mapCategoriesToDTO(categoryRepository.findAllByParentId(id));
    }

    public List<CategoryDTO> getAllSubcategories(Integer id) {
        List<Category> subcategories = categoryRepository.findAllByParentId(id);
        List<CategoryDTO> allSubcategories = categoryDTOMapper.mapCategoriesToDTO(subcategories);
        if (!subcategories.isEmpty()) {
            subcategories.forEach(category -> {
                allSubcategories.addAll(categoryDTOMapper.mapCategoriesToDTO(categoryRepository.findAllByParentId(category.getId())));
            });
        }
        return allSubcategories;
    }

//    public List<Category> getSubcategories(Long id) {
//        return categoryRepository.findByParentCategory(getCategoryById(id));
//    }
}

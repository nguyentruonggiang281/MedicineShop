package com.shop.medicineshop.controller;

import com.shop.medicineshop.model.category.Category;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.response.category.CategoryDTO;
import com.shop.medicineshop.response.product.ProductDTO;
import com.shop.medicineshop.service.CategoryService;
import com.shop.medicineshop.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        if (categoryService.getAllCategories().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        if (categoryService.getCategoryById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }


    //    lấy danh mục con của danh mục ID
    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryDTO>> getSubcategories(@PathVariable Integer id) {
        if (categoryService.getSubcategories(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(categoryService.getSubcategories(id));
    }

    // lấy tất cả danh mục nằm trong danh mục ID
    @GetMapping("/{id}/all-subcategories")
    public ResponseEntity<List<CategoryDTO>> getAllSubcategories(@PathVariable Integer id) {
        if (categoryService.getAllSubcategories(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(categoryService.getAllSubcategories(id));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Integer id) {
        List<CategoryDTO> categoryDTO = categoryService.getAllSubcategories(id);
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (categoryDTO.isEmpty()) {
            productDTOList.addAll(productService.getProductsByCategoryId(id));
        } else {
            categoryDTO.forEach(category -> {
                productDTOList.addAll(productService.getProductsByCategoryId(category.id()));
            });
        }
        if (productDTOList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productDTOList);
    }

//    @PostMapping("/")
//    public Category saveCategory(@RequestBody Category category) {
//        return categoryService.saveCategory(category);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
//        categoryService.updateCategory(id, category);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCategory(@PathVariable Long id) {
//        categoryService.deleteCategory(id);
//    }


}


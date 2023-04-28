package com.shop.medicineshop.controller;

import com.shop.medicineshop.model.category.Category;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.category.CategoryService;
import com.shop.medicineshop.model.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{id}/subcategories")
    public List<Category> getSubcategories(@PathVariable Long id) {
        return categoryService.getSubcategories(id);
    }
    /**
     * Truy xuất danh sách các sản phẩm thuộc danh mục có ID được chỉ định.
     *
     * @param id the ID of the category to retrieve products for
     * @return a list of Product objects
     */
    @GetMapping("/{id}/products")
    public List<Product> getProductsByCategoryId(@PathVariable Integer id) {
        return productService.getProductsByCategoryId(id);
    }

    @PostMapping("/")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }


}


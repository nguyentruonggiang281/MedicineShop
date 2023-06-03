package com.shop.medicineshop.controller;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import com.shop.medicineshop.response.product.AssetDTO;
import com.shop.medicineshop.response.product.ProductDTO;
import com.shop.medicineshop.response.product.ProductDetailDTO;
import com.shop.medicineshop.response.product.UnitDTO;
import com.shop.medicineshop.service.product.AssetService;
import com.shop.medicineshop.service.product.ProductDetailService;
import com.shop.medicineshop.service.product.ProductService;
import com.shop.medicineshop.service.product.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/products")
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private UnitService unitService;

    //    lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        if (productService.getAllProducts().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productService.getAllProducts());
    }


    //    Truy xuất sản phẩm theo ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
//        ProductDTO product = productService.getProductById(id);
//        if (product == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(product);
//
//    }

    //    Search sản phẩm theo tên
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam("search") String query) {
        if (productService.searchProducts(query).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productService.searchProducts(query));
    }

    //    Truy xuất hình ảnh sản phẩm theo ID
    @GetMapping("/{id}/assets")
    public ResponseEntity<List<AssetDTO>> getProductAssets(@PathVariable Integer id) {
        if (assetService.getProductAssets(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(assetService.getProductAssets(id));
    }

    // chi tiết sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProductDetails(@PathVariable Integer id) {
        if (productDetailService.getProductDetails(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productDetailService.getProductDetails(id));
    }

    //    Truy xuất đơn vị của thuốc theo ID
    @GetMapping("/{id}/units")
    public ResponseEntity<List<UnitDTO>> getProductUnits(@PathVariable Integer id) {
        if (unitService.getProductUnits(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(unitService.getProductUnits(id));
    }

    // lấy danh sách các sản phẩm theo #tag tre-em, me-va-be, nguoi-cao-tuoi, nguoi-bi-tieu-duong
    @GetMapping("/tags/{tagSlug}")
    public ResponseEntity<List<ProductDTO>> getProductsByTag(@PathVariable String tagSlug) {
        if (productService.getProductsByTag(tagSlug).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productService.getProductsByTag(tagSlug));
    }

    @GetMapping("/{id}/quantity-units")
    public ResponseEntity<?> getProductQuantity(@PathVariable Integer id) {
        List<UnitDTO> unitDTOs = productService.getProductQuantityPerUnit(id);
        if (unitDTOs.isEmpty() || unitDTOs == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(unitDTOs);
    }


    /**
     * Thêm một sản phẩm mới vào hệ thống.
     *
     * @param product the new Product object to add
     * @return a ResponseEntity indicating success or failure of the operation
     */

//    @PostMapping("/")
//    public ResponseEntity<?> addProduct(@RequestBody Product product) {
//        productService.addProduct(product);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    /**
//     * Cập nhật một sản phẩm hiện có với ID được chỉ định.
//     *
//     * @param id      the ID of the product to update
//     * @param product the updated Product object
//     * @return a ResponseEntity indicating success or failure of the operation
//     */
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
//        productService.updateProduct(id, product);
//        return ResponseEntity.ok().build();
//    }
//
//    /**
//     * Xóa một sản phẩm có ID được chỉ định.
//     *
//     * @param ids the list ID of the product to delete
//     * @return a ResponseEntity indicating success or failure of the operation
//     */
//    @DeleteMapping("/{ids}")
//    public ResponseEntity<?> deleteProduct(@PathVariable List<Long> ids) {
//        productService.deleteProduct(ids);
//        return ResponseEntity.ok().build();
//    }


}


//@RestController
//@RequestMapping("/dashboard")
//@CrossOrigin("http://localhost:3000")
//public class ProductController {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @PostMapping("/product")
//    Product newProduct(@RequestBody Product newProduct) {
//        return productRepository.save(newProduct);
//    }
//
//    @GetMapping("/product")
//    List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    @GetMapping("/product/{id}")
//    Product getProductById(@PathVariable Long id) {
//        return productRepository.findById(id)
//                .orElseThrow(() -> new ProductNotFoundException(id));
//    }
//
////    @PutMapping("/product/{id}")
////    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
////        return productRepository.findById(id)
////                .map(product -> {
////                    product.setName(newProduct.getName());
////                    product.setUsedTime(newProduct.getUsedTime());
////                    product.setStatus(newProduct.getStatus());
////                    product.setPrice(newProduct.getPrice());
////                    return productRepository.save(product);
////                }).orElseThrow(() -> new ProductNotFoundException(id));
////    }
//
//    @DeleteMapping("/product/{id}")
//    String deleteProduct(@PathVariable Long id){
//        if(!productRepository.existsById(id)){
//            throw new ProductNotFoundException(id);
//        }
//        productRepository.deleteById(id);
//        return  "Product with id "+id+" has been deleted success.";
//    }
//
//    @DeleteMapping("/products/{ids}")
//    String deleteListProduct(@PathVariable("ids") List<Long> ids){
//        ids.forEach(d->{
//            if(productRepository.existsById(d)){
//                productRepository.deleteById(d);
//            }
//        });
//        return  "Product with id "+ids+" has been deleted success.";
//    }
//}


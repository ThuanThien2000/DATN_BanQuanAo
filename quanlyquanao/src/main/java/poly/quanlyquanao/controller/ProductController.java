package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.model.Category;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.service.CategoryService;
import poly.quanlyquanao.service.ProductService;
import poly.quanlyquanao.dto.CategoryDTO;
import poly.quanlyquanao.dto.ProductDTO;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    // Thêm sản phẩm mới localhost:8080/api/products/add
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    // Cập nhật sản phẩm – localhost:8080/api/products/update/1 (1 là ví dụ cho id muốn sửa)
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product updated = productService.updateProduct(id, productDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xóa mềm sản phẩm – localhost:8080/api/products/update/1 (1 là ví dụ cho id muốn xóa)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean result = productService.softDeleteProduct(id);
        return result ? ResponseEntity.ok("Deleted (soft)") : ResponseEntity.notFound().build();
    }

    // Xem chi tiết sản phẩm – localhost:8080/api/products/detail/1
    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        return productDTO != null ? ResponseEntity.ok(productDTO) : ResponseEntity.notFound().build();
    }

    // Lấy tất cả sản phẩm đang bán localhost:8080/api/products/all
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllActiveProducts() {
        return ResponseEntity.ok(productService.getAllActiveProductDTOs());
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllDTO());
    }
    @PutMapping("/feature/{id}")
    public ResponseEntity<String> featureProduct(@PathVariable Long id) {
        boolean success = productService.featureProduct(id);
        if (!success) {
            // Có thể do đã đủ 8 sản phẩm featured hoặc không tìm thấy sản phẩm
            return ResponseEntity.badRequest().body("Không thể đánh dấu nổi bật: đã đủ 10 sản phẩm hoặc sản phẩm không tồn tại/không hoạt động.");
        }
        return ResponseEntity.ok("Product featured successfully");
    }
    @PutMapping("/unfeature/{id}")
    public ResponseEntity<String> unfeatureProduct(@PathVariable Long id) {
        boolean success = productService.unfeatureProduct(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Product unfeatured successfully");
    }
    // Cảnh báo sản phẩm sắp hết hàng localhost:8080/api/products/low-stock?threshold=5
    @GetMapping("/lowstock")
    public ResponseEntity<List<Product>> getLowStockProducts(@RequestParam(defaultValue = "5") int threshold) {
        return ResponseEntity.ok(productService.getLowStockProducts(threshold));
    }
}

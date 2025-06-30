package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Thêm sản phẩm mới localhost:8080/api/products/add
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Cập nhật sản phẩm – localhost:8080/api/products/update/1 (1 là ví dụ cho id muốn sửa)
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
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
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Lấy tất cả sản phẩm đang bán localhost:8080/api/products/all
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllActiveProducts() {
        return ResponseEntity.ok(productService.getAllActiveProducts());
    }

    // Cảnh báo sản phẩm sắp hết hàng localhost:8080/api/products/low-stock?threshold=5
    @GetMapping("/lowstock")
    public ResponseEntity<List<Product>> getLowStockProducts(@RequestParam(defaultValue = "5") int threshold) {
        return ResponseEntity.ok(productService.getLowStockProducts(threshold));
    }
}

package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ProductDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/product-details")
public class ProductDetailController {

    @Autowired
    private ProductDetailService service;

    // ✅ Thêm biến thể theo productId localhost:8080/api/product-details/add/1
    @PostMapping("/add/{productId}")
    public ResponseEntity<ProductDetail> add(@PathVariable Long productId, @RequestBody ProductDetail detail) {
        ProductDetail created = service.addByProductId(productId, detail);
        return created != null ? ResponseEntity.ok(created) : ResponseEntity.badRequest().build();
    }

    // ✅ Cập nhật biến thể localhost:8080/api/product-details/update/{id}
    @PutMapping("/update/{id}") 
    public ResponseEntity<ProductDetail> update(@PathVariable Long id, @RequestBody ProductDetail detail) {
        ProductDetail updated = service.update(id, detail);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ✅ Xoá mềm biến thể localhost:8080/api/product-details/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = service.softDelete(id);
        return deleted ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }

    // ✅ Xem chi tiết biến thể localhost:8080/api/product-details/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetail> findById(@PathVariable Long id) {
        ProductDetail detail = service.findById(id);
        return detail != null ? ResponseEntity.ok(detail) : ResponseEntity.notFound().build();
    }

    // ✅ Lấy danh sách theo Product ID localhost:8080/api/product-details/by-product/{productId}
    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<ProductDetail>> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getByProductId(productId));
    }

    // ✅ Lấy danh sách còn hoạt động theo Product ID localhost:8080/api/product-details/active/by-product/1
    @GetMapping("/active/by-product/{productId}")
    public ResponseEntity<List<ProductDetail>> getActiveByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getActiveByProductId(productId));
    }
}

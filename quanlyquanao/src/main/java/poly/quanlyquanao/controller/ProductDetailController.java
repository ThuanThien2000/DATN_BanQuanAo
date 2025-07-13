package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ProductDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/product/{productId}/details")
@CrossOrigin(origins = "*")
public class ProductDetailController {

    @Autowired
    private ProductDetailService service;

    @GetMapping("")
    public ResponseEntity<List<ProductDetailDTO>> getActiveByProductId(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(service.getActiveByProductId(productId));
    }

    // Thêm biến thể cho productId
    // POST: locolhost:8080/api/product/{productId}/details/add
    @PostMapping("/add")
    public ResponseEntity<ProductDetail> add(@PathVariable("productId") Long productId,
                                             @RequestBody ProductDetailDTO detail) {
        ProductDetail created = service.addByProductId(productId, detail);
        return created != null ? ResponseEntity.ok(created) : ResponseEntity.badRequest().body(null);
    }

    //  Cập nhật biến thể theo detailId
    // PUT: locolhost:8080/api/product/{productId}/details/update/{detailId}
    @PutMapping("/update/{detailId}")
    public ResponseEntity<ProductDetail> update(@PathVariable("detailId") Long id,
                                                @RequestBody ProductDetailDTO detail) {
        ProductDetail updated = service.update(id, detail);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // Xoá mềm biến thể theo detailId
    // DELETE: locolhost:8080/api/product/{productId}/details/delete/{detailId}
    @DeleteMapping("/delete/{detailId}")
    public ResponseEntity<String> delete(@PathVariable("productId") Long productId,
                                         @PathVariable("detailId") Long detailId) {
        boolean deleted = service.softDelete(productId, detailId);
        return deleted ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }

    //Xem chi tiết biến thể theo detailId
    // GET: locolhost:8080/api/product/{productId}/details/{detailId}
    @GetMapping("/{detailId}")
    public ResponseEntity<ProductDetailDTO> findById(@PathVariable("detailId") Long id) {
        ProductDetailDTO detail = service.findById(id);
        return detail != null ? ResponseEntity.ok(detail) : ResponseEntity.notFound().build();
    }
}
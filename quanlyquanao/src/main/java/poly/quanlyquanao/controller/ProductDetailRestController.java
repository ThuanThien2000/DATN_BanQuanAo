package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.Impl.IProductDetailService; // Đã thêm import

import java.util.List;

@RestController
@RequestMapping("/api/product-detail") // Endpoint cho ProductDetail
public class ProductDetailRestController {

    @Autowired
    IProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<List<ProductDetail>> getAllProductDetails() {
        List<ProductDetail> productDetails = productDetailService.getAllProductDetail();
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetail> getProductDetailById(@PathVariable("id") Long id) {
        try {
            ProductDetail productDetail = productDetailService.getProductDetailById(id);
            return new ResponseEntity<>(productDetail, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Xử lý khi không tìm thấy chi tiết sản phẩm
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDetail> addProductDetail(@RequestBody ProductDetail productDetail) {
        ProductDetail newProductDetail = productDetailService.addProductDetail(productDetail);
        return new ResponseEntity<>(newProductDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetail> updateProductDetail(@PathVariable("id") Long id, @RequestBody ProductDetail productDetail) {
        ProductDetail updatedProductDetail = productDetailService.updateProductDetail(id, productDetail);
        if (updatedProductDetail != null) { // Service trả về null nếu không tìm thấy
            return new ResponseEntity<>(updatedProductDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductDetail(@PathVariable("id") Long id) {
        try {
            productDetailService.deleteProductDetail(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            // Xử lý khi không tìm thấy chi tiết sản phẩm để xóa
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

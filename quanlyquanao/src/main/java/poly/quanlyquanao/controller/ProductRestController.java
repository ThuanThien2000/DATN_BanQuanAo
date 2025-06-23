package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.service.Impl.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    private IProductService productService;

    /**
     * Lấy tất cả sản phẩm.
     * @return Danh sách sản phẩm
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Lấy sản phẩm theo ID.
     * @param id ID của sản phẩm
     * @return Sản phẩm hoặc HttpStatus.NOT_FOUND nếu không tìm thấy
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) { // Đã thay đổi kiểu tham số
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Log lỗi nếu cần, sau đó trả về NOT_FOUND
            // System.err.println("Lỗi khi tìm sản phẩm: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Thêm sản phẩm mới. Frontend sẽ gửi Product object với các imgUrl là chuỗi.
     * @param product Đối tượng Product được gửi từ frontend
     * @return Sản phẩm đã được tạo với ID và HttpStatus.CREATED
     */
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.addProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Cập nhật sản phẩm theo ID. Frontend sẽ gửi Product object với các imgUrl là chuỗi.
     * @param id ID của sản phẩm cần cập nhật (từ URL path)
     * @param product Đối tượng Product chứa thông tin cập nhật (từ Request Body)
     * @return Sản phẩm đã được cập nhật và HttpStatus.OK, hoặc HttpStatus.NOT_FOUND nếu không tìm thấy
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Xóa sản phẩm theo ID.
     * @param id ID của sản phẩm cần xóa
     * @return HttpStatus.NO_CONTENT nếu xóa thành công, hoặc HttpStatus.NOT_FOUND nếu không tìm thấy
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Đã xóa sản phẩm thành công!!!", HttpStatus.OK); // 200 OK với thông báo
        } catch (RuntimeException e) {
            // Xử lý khi không tìm thấy sản phẩm để xóa
            return new ResponseEntity<>("Không tìm thấy sản phẩm với id là " + id, HttpStatus.NOT_FOUND); // 404 Not Found với thông báo lỗi
        }
    }
}

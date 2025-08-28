package poly.quanlyquanao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.CategoryDTO;
import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.service.CategoryService;
import poly.quanlyquanao.service.ShopService;

@RestController
@RequestMapping("/api/shop")
@CrossOrigin(origins = "*")
public class ShopController {
	@Autowired
   private ShopService shopService;
       @Autowired
    private CategoryService categoryService;
	@GetMapping("")
	public ResponseEntity<List<ProductInfo>> getALLProductInfo() {
        return ResponseEntity.ok(shopService.getAllProductInfo());
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductInfo>> searchProducts(@RequestParam(value = "keyword") String keyword) {
        List<ProductInfo> products = shopService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/user-type")
    public ResponseEntity<List<ProductInfo>> getProductsByUserType(@RequestParam(value = "userType") String userType) {
        List<ProductInfo> products = shopService.getProductsByUserType(userType);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllDTO());
    }


}

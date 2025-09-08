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
        @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllDTO());
    }

}

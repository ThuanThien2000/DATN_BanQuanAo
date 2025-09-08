package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.mapper.ProductInfoMapper;
import poly.quanlyquanao.service.ProductService;
import poly.quanlyquanao.service.Impl.HomeServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
public class HomeController {

	@Autowired
	private HomeServiceImpl homeService;
	// Lọc sản phẩm nổi bật (isFeatured = true)
	@GetMapping("/featured")
	public ResponseEntity<List<ProductInfo>> getFeaturedProducts(@RequestParam(value = "limit") Integer limit) {
		List<ProductInfo> featuredProducts = homeService.getFeaturedProducts(limit);
		return ResponseEntity.ok(featuredProducts);
	}
	// Trả về 3 sản phẩm mới nhất (dựa vào ngày tạo)
	@GetMapping("/newest")
	public ResponseEntity<List<ProductInfo>> getNewestProducts(@RequestParam(value = "limit") Integer limit) {
		List<ProductInfo> newestProducts = homeService.getNewArrivals(limit);
		return ResponseEntity.ok(newestProducts);
	}
		@GetMapping("/user-type")
	public ResponseEntity<List<ProductInfo>> getProductsByUserType(@RequestParam(value = "userType") String userType) {
		List<ProductInfo> products = homeService.getByUserType(userType);
		return ResponseEntity.ok(products);
	}
}

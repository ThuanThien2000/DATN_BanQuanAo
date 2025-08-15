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
@RequestMapping("api/home")
public class HomeController {

	@Autowired
	private HomeServiceImpl homeService;
	// Lọc sản phẩm nổi bật (isFeatured = true)
	@GetMapping("/featured")
	public ResponseEntity<List<ProductInfo>> getFeaturedProducts(@RequestParam(value = "limit") Integer limit) {
		List<ProductInfo> featuredProducts = homeService.getFeaturedProducts(limit);
		return ResponseEntity.ok(featuredProducts);
	}
	// Trả về 3 sản phẩm bán chạy nhất (dựa vào số lượng hóa đơn chi tiết)
	@GetMapping("/bestsellers")
	public ResponseEntity<List<ProductInfo>> getBestSellers(@RequestParam(value = "limit") Integer limit) {
		List<ProductInfo> bestSellers = homeService.getBestSellers(limit);
		return ResponseEntity.ok(bestSellers);
	}
	// Trả về 3 sản phẩm mới nhất (dựa vào ngày tạo)
	@GetMapping("/newest")
	public ResponseEntity<List<ProductInfo>> getNewestProducts(@RequestParam(value = "limit") Integer limit) {
		List<ProductInfo> newestProducts = homeService.getNewArrivals(limit);
		return ResponseEntity.ok(newestProducts);
	}
	// Lấy sản phẩm theo loại người dùng
	@GetMapping("/limited-user-type")
	public ResponseEntity<List<ProductInfo>> getProductsByLimitedUserType(@RequestParam(value = "userType") String userType, @RequestParam(value = "limit") Integer limit) {
		List<ProductInfo> products = homeService.getByLimitedUserType(userType, limit);
		return ResponseEntity.ok(products);
	}
		@GetMapping("/user-type")
	public ResponseEntity<List<ProductInfo>> getProductsByUserType(@RequestParam(value = "userType") String userType) {
		List<ProductInfo> products = homeService.getByUserType(userType);
		return ResponseEntity.ok(products);
	}
}

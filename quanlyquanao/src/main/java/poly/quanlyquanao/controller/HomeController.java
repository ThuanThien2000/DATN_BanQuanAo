package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ProductService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/home")
public class HomeController {

	@Autowired
	private ProductService productService;

	// Trả về toàn bộ sản phẩm đang hoạt động (có thể lọc theo userType nếu cần)
	@GetMapping
	public List<ProductDTO> getHomeProducts(@RequestParam(name = "userType", required = false) String userType) {
		List<ProductDTO> products = productService.getAllActiveProductDTOs();

		if (userType != null && !userType.isEmpty()) {
			return products.stream().filter(p -> userType.equalsIgnoreCase(p.getUserType()))
					.collect(Collectors.toList());
		}
		return products;
	}

	// Lọc sản phẩm nổi bật (isFeatured = true)
	@GetMapping("/featured")
	public List<ProductDTO> getFeaturedProducts() {
		return productService.getAllActiveProductDTOs().stream().filter(p -> Boolean.TRUE.equals(p.getIsFeatured()))
				.collect(Collectors.toList());
	}
	// Trả về 3 sản phẩm bán chạy nhất (dựa vào số lượng hóa đơn chi tiết)
}

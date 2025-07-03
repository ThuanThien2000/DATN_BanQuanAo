package poly.quanlyquanao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.service.ShopService;

@RestController
@RequestMapping("/api/customer/products")
public class ShopController {
	@Autowired
    private ShopService shopService;
	
	@GetMapping("/filter-full")
	public List<Product> filterProducts(
	        @RequestParam(required = false) Long categoryId,
	        @RequestParam(required = false) Double minPrice,
	        @RequestParam(required = false) Double maxPrice,
	        @RequestParam(required = false) String size,
	        @RequestParam(required = false) String style,
	        @RequestParam(defaultValue = "0") int page
	) {
	    return shopService.filterProducts(categoryId, minPrice, maxPrice, size, style, page);
	}

}

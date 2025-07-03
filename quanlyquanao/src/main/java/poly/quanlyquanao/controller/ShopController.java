package poly.quanlyquanao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.service.ShopService;

@RestController
@RequestMapping("/api/customer/products")
public class ShopController {
	@Autowired
    private ShopService shopService;
	
	@GetMapping("/filter")
    public Page<Product> filterProducts(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) List<String> sizes,
            @RequestParam(required = false) List<String> styles,
            @RequestParam(defaultValue = "0") int page
    ) {
        return shopService.filterProducts(minPrice, maxPrice, sizes, styles, page);
    }

}
